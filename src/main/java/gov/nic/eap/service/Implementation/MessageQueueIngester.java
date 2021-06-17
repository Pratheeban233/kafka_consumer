package gov.nic.eap.service.Implementation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import gov.nic.eap.constant.CommonConstant;
import gov.nic.eap.data.TaskDetailsConfiguration;
import gov.nic.eap.db.JdbcConnectionUtil;
import gov.nic.eap.db.RrsDBQueryProcessor;
import gov.nic.eap.service.Ingester;
import gov.nic.eap.service.RrsRequestValidator;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@SuppressWarnings("unchecked")
public class MessageQueueIngester implements Ingester {

	public static final String MAIL = "mail";
	public static final String SMS = "sms";
	public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@Autowired
	JdbcConnectionUtil jdbcConnectionUtil;

	@Autowired
	RrsDBQueryProcessor rrsDBQuery;

	@Autowired
	private TaskDetailsConfiguration taskDetailsConfiguration;

	@Override
	public void processConsumerMessages(String message) throws Exception {

		List<?> listofmaps = new ObjectMapper().readValue(message, new TypeReference<List<?>>() {
		});
		if (message.contains("mailid")) {
			log.debug("listOfmails : {}", listofmaps);
			updateProcessedRecords (MAIL, (List<Map<String, Object>>) listofmaps);
		}
		if (message.contains("smsid")) {
			log.debug("listOfsms : {}", listofmaps);
			updateProcessedRecords (SMS, (List<Map<String, Object>>) listofmaps);
		}
	}

	public List<Map<String, Object>> updateProcessedRecords(String key, List<Map<String, Object>> message) throws Exception {
		int noOfUpdatedRecords = 0;
		Map<String, String> allRequestParams = new HashMap<>();
		Optional<TaskDetailsConfiguration.Config> configOptional = taskDetailsConfiguration.getJobConfigs(key);
		if (configOptional.isPresent()) {
			TaskDetailsConfiguration.Config config = configOptional.get();
			if (Objects.nonNull(message)) {
				allRequestParams.put("processid", String.valueOf(2));
				allRequestParams.put("isexecuted", String.valueOf(true));
				allRequestParams.put("executeddate", LocalDateTime.now().format(DATE_TIME_FORMATTER));
				if (key.equalsIgnoreCase(MAIL) || key.equalsIgnoreCase(SMS)) {
					for (Map<String, Object> n : message) {
						allRequestParams.put("id", n.get("id").toString());
						noOfUpdatedRecords = getRrsRequestValidator(key, message, allRequestParams, config, noOfUpdatedRecords);
					}
					log.info(noOfUpdatedRecords + " : " + key + " Record's Updated.");
				}else
				return CommonConstant.invalidList;
			}else
			return CommonConstant.queryList;
		}
		return CommonConstant.mandatoryList;
	}

	private int getRrsRequestValidator(String key, List<Map<String, Object>> message, Map<String, String> allRequestParams,
			TaskDetailsConfiguration.Config config, int noOfUpdatedRecords) throws Exception {
		List<Map<String, Object>> updateResult;
		RrsRequestValidator requestValidator = new RrsRequestValidator(key, config, rrsDBQuery, allRequestParams);
		config = requestValidator.updateRequestParamsValidation();
		if (config.isRequestValidation() && Objects.nonNull(message)) {
			updateResult = jdbcConnectionUtil.getResultSets(config.getUpdateQuery(), config.getQueryParams());
			for (Map<String, Object> result : updateResult) {
				if (result.get("No. of Records Affected").equals(1)) {
					noOfUpdatedRecords++;
				}
			}
		}
		return noOfUpdatedRecords;
	}
}

package gov.nic.eap.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonConstant {

	public static final String AUTHORIZATION_HEADER = "auth_token";

	public static final List<Map<String, Object>> invalidList = new ArrayList<>();
	public static final Map<String, Object> invalidMap = new HashMap<>();

	public static final List<Map<String, Object>> mandatoryList = new ArrayList<>();
	public static final Map<String, Object> mandatoryMap = new HashMap<>();

	public static final List<Map<String, Object>> mismatchList = new ArrayList<>();
	public static final Map<String, Object> mismatchMap = new HashMap<>();

	public static final List<Map<String, Object>> tryList = new ArrayList<>();
	public static final Map<String, Object> tryMap = new HashMap<>();

	public static final List<Map<String, Object>> queryList = new ArrayList<>();
	public static final Map<String, Object> queryMap = new HashMap<>();

	public static final List<Map<String, Object>> methodList = new ArrayList<>();
	public static final Map<String, Object> methodMap = new HashMap<>();

	public static final List<Map<String, Object>> authList = new ArrayList<>();
	public static final Map<String, Object> authMap = new HashMap<>();

	static {
		invalidMap.put(ResponseKey.STATUS, ResponseValue.INVALID);
		invalidList.add(invalidMap);

		mandatoryMap.put(ResponseKey.STATUS, ResponseValue.FILL_MANDATORY_FIELDS);
		mandatoryList.add(mandatoryMap);

		mismatchMap.put(ResponseKey.STATUS, ResponseValue.FIELDS_TYPE_MISMATCH);
		mismatchList.add(mismatchMap);

		tryMap.put(ResponseKey.STATUS, ResponseValue.PLEASE_TRY_LATER);
		tryList.add(tryMap);

		queryMap.put(ResponseKey.STATUS, ResponseValue.QUERY_ERROR);
		queryList.add(queryMap);

		methodMap.put(ResponseKey.STATUS, ResponseValue.METHOD_ERROR);
		methodList.add(methodMap);

		authMap.put(ResponseKey.STATUS, ResponseValue.AUTH_ERROR);
		authList.add(authMap);
	}

	public static class ResponseKey {
		public static final String STATUS = "Status";
	}

	public static class ResponseValue {
		public static final String INVALID = "Invalid";
		public static final String FILL_MANDATORY_FIELDS = "Fill Mandatory Parameters";
		public static final String FIELDS_TYPE_MISMATCH = "Mismatch of Parameters Types";
		public static final String PLEASE_TRY_LATER = "Please Try Later";
		public static final String QUERY_ERROR = "Problem in Displaying Data";
		public static final String METHOD_ERROR = "Method Not Supported";
		public static final String AUTH_ERROR = "Not Authenticated";
	}
}

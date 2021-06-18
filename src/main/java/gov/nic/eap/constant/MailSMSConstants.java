package gov.nic.eap.constant;

public class MailSMSConstants {

	public static class Tableconstants {
		public static final String REGISTRATION = "Registration";
		public static final String LOGIN = "login";
	}

	public static class MailProperties {
		public static final String USER = "mail.smtp.user";
		public static final String PASSWORD = "mail.smtp.password";
		public static final String PORT = "mail.smtp.port";
		public static final String FROM = "mail.smtp.from";
		public static final String SOCKETPORT = "mail.smtp.socketFactory.port";
		public static final String SOCKETCLASS = "mail.smtp.socketFactory.class";
		public static final String DEFAULTSOCKETFACTORY = "javax.net.ssl.SSLSocketFactory";
		public static final String SOCKETFALLBACK = "mail.smtp.socketFactory.fallback";
		public static final String DEFAULTSOCKETFALLBACK = "false";
		public static final String AUTH = "mail.smtp.auth";
		public static final String STARTTLS = "mail.smtp.starttls.enable";
		public static final String HOST = "mail.smtp.host";

		public static class Port {
			public static final Long FOURSIXTYFIVE = 465L;
		}
	}

	public static class SMSProperties {
		public static final String USER = "sms.user";
		public static final String PASSWORD = "sms.password";
		public static final String SENDERID = "sms.senderid";
		public static final String CONTACTNO = "sms.contactno";
		public static final String SPLITMSGTYPE = "sms.splitmsgtype";
		public static final String SERVERURL = "sms.serverurl";
		public static final String PORT = "sms.port";
	}

	public static class Tags {
		public static final String LOGO = "^logo^";
		public static final String TOPHEADER = "topheader";
		public static final String SERVERDATE = "serverdate";
		public static final String IPADDRESS = "ipaddress";
		public static final String ASONDATE = "asondate";
		public static final String FAILEDATTEMPTS = "failedattempts";
		public static final String SITENAME = "sitename";

		public static class Verification {
			public static final String VERIFICATIONCODE = "verificationcode";
			public static final String MOBILEVERIFICATIONCODE = "mobileverificationcode";
		}

		public static class User {
			public static final String NAME = "name";
			public static final String LOGINID = "loginid";
			public static final String REASON = "reason";
			public static final String PASSWD = "password";
		}

		public static class Debar {
			public static final String COMPANYNAME = "companyname";
			public static final String DEBARREASON = "debarreason";
			public static final String FROMDATE = "fromdate";
			public static final String TODATE = "todate";
		}

		public static class Company {
			public static final String COMPANY_NAME = "companyname";
			public static final String BID_BOND_AMOUNT = "amount";
			public static final String INSTRUMENT_NO = "instnumber";
			public static final String REMARKS = "remarks";
			public static final String PORTAL = "portal";
		}

		public static class Tender {
			public static final String TENDER_NUMBER = "tenderno";
			public static final String REASON = "reason";
			public static final String COMMENTS = "comments";
			public static final String TENDER_ID = "tenderid";
			public static final String APPROVAL_STATUS = "approvalstatus";
			public static final String INVITING_OFFICER = "invitingofficer";
			public static final String INVITING_OFFICER_ADDRESS = "address";
			public static final String TENDER_OPEN_DATE = "tenderopendate";
			public static final String TENDER_PUBLISH_DATE = "tenderpublishdate";
			public static final String BID_SUBMISSION_START_DATE = "bidsubmissionstartdate";
		}

		public static class Certificate {
			public static final String CERT_TYPE = "certtype";
			public static final String CERT_NAME = "certname";
		}

		public static class Corrigendum {
			public static final String CORRIGENDUM_TYPE = "corrigendumtype";
			public static final String CORRIGENDUM_TITLE = "corrigendumtitle";
			public static final String REASON = "reason";
		}

		public static class OTPConstants {

			public static final String SITE_ID = "siteId";
			public static final String USER_ID = "userId";
			public static final String LOGIN_ID = "loginId";
			public static final String MOBILE_NO = "mobileNo";
			public static final String EMAIL_ID = "emailId";
			public static final String OTP_CATEGORY_ID = "otpCategoryId";
			public static final String OTP_TYPE = "otpType";
			public static final String MOBILE_OTP = "mobileOtp";
			public static final String EMAIL_OTP = "emailOtp";
			public static final String IP_ADDRESS = "ipAddress";
			public static final String MAC_ADDRESS = "macAddress";
			public static final String OTP_1 = "otp1";
			public static final String OTP_2 = "otp2";
			public static final String OTP_CATEGORY_SAME = "same";
			public static final String OTP_CATEGORY_DIFFERENT = "different";
			private OTPConstants() {
			}
		}
	}

	public static class MailSmsEvent {
		public static final String GENERATE_FORGOT_PWD_VERIFICATION_CODE = "GenerateForgotPwdVeriCode";
		public static final String OTP_FOR_FAILED_LOGIN_ATTEMPTS = "OtpForFailedLoginAttempts";
		public static final String GENERATE_FORGOT_PROFILE_PWD_VERIFICATION_CODE = "GenerateForgotProfilePwdVeriCode";
		public static final String OTP_FOR_BIDDER_LOGIN = "OtpForBidderLogin";
		public static final String OTP_FOR_CHANGE_MOBILE_NO_EMAIL_ID = "OtpForChangeMobilenoEmailId";
		public static final String NOTIFICATION_FOR_FAILED_LOGIN_ALERT = "NotificationForFailedLoginAlert";
		public static final String CONF_OF_UPDATE_MOBILE_NO_EMAILID = "ConfOfUpdateMobileNoEmailId";
		public static final String DEBAR_COMPANY = "DebarredCompany";
		public static final String REVOKE_COMPANY = "RevokeCompany";
		public static final String CONF_OF_USER_ENROLLMENT = "ConfOfUserEnrollment";
		public static final String CONF_OF_COMPANY_REGISTRATION = "ConfirmationOfCompanyRegistration";
		public static final String BID_BOND_CREATION = "BidBondCreation";
		public static final String BID_BOND_APPROVED = "BidBondApproved";
		public static final String CONF_OF_USER_BLOCK = "ConfirmationOfUserBlocked";
		public static final String CONF_OF_USER_UNBLOCK = "ConfirmationOfUserUnblocked";
		public static final String SEND_FOR_MODIFICATION_NOTIFICATION = "SendForModification";
		public static final String SEND_FOR_BID_BOND_MODIFICATION = "SendForBidBondModification";
		public static final String SEND_FOR_APPROVAL_NOTIFICATION = "SendForApproval";
		public static final String SEND_TO_PUBLISH_NOTIFICATION = "SendToPublish";
		public static final String REVIEWER_APPROVAL_NOTIFICATION = "TenderReviewerApprovalStatus";
		public static final String MODIFY_TENDER_NOTIFICATION = "CreatorModifyTender";
		public static final String PUBLISHED_TENDER_NOTIFICATION_TO_CREATOR = "AckOfTenderPublishedToCreator";
		public static final String PUBLISHED_TENDER_NOTIFICATION_TO_OPENER = "NotificationOfTenderPublishedToOpener";
		public static final String PUBLISHED_TENDER_NOTIFICATION_TO_BIDDER = "NotificationOfTenderPublishedToTrader";
		public static final String CERTIFICATE_EXPIRED_NOTIFICATION = "CertificateExpiredNotification";
		public static final String NOTIFICATION_OF_PASSWD_RESET_BY_ADMIN = "NotificationOfPasswdResetByAdmin";
		public static final String SEND_FOR_CORRIGENDUM_MODIFICATION = "SendForCorrigendumModification";
		public static final String SEND_CORRIGENDUM_TO_PUBLISH = "SendCorrigendumToPublish";
		public static final String PUBLISHED_CORRIGENDUM_NOTIFICATION_TO_CREATOR = "AckOfCorrigendumPublishedToCreator";
		public static final String GENERATE_OTP_CHANGE_MOBILENO_OR_EMAILID = "GenerateOtpChangeMobileNoOrEmailId";
		public static final String CONF_OF_MOBILE_OR_EMAILID_UPDATE = "ConfOfMobileOrEmailIdUpdate";
		public static final String PRE_OPENING_SEND_FOR_REVIEWER_NOTIFICATION = "PreOpeningSendForReviewerNotification";
		public static final String PRE_OPENING_FREEZE_RECYCLE_NOTIFICATION = "PreOpeningFreezeRecycleNotification";
	}

	public static class validationMessage {
		public static final String VALIDITY_EXPIRED = "validity time for otp is expired.";
		public static final String NO_OF_ATTEMPTS = "You have reached the number of attempts.";
		public static final String OTP_NOT_MATCHED = "Entered otp is not matched";
		public static final String OTP_NOT_PRESENT = "Your otp is not yet generated.";
		private validationMessage() {
		}
	}

}
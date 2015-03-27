
    drop table HS_CA_EVENTDETAILS cascade constraints;

    drop table HS_CA_EVENTEXTDETAILS cascade constraints;

    drop table HS_CA_EVENTMESSAGEFIELDS cascade constraints;

    drop table HS_CA_EVENTMESSAGES cascade constraints;

    drop table HS_CA_EVENTS cascade constraints;

    drop table HS_SP_SECURITYPORTFOLIO cascade constraints;

    drop table TB_ANSWERS cascade constraints;

    drop table TB_APPLICATIONS cascade constraints;

    drop table TB_CATEGORIES cascade constraints;

    drop table TB_CATEGORY_PERMISSION cascade constraints;

    drop table TB_CA_ALERTS cascade constraints;

    drop table TB_CA_ANSWERTYPES cascade constraints;

    drop table TB_CA_DOMAINCLUSTERFORMATS cascade constraints;

    drop table TB_CA_EVENTCONFIGFIELDS cascade constraints;

    drop table TB_CA_EVENTCONFIGMSGFLDS cascade constraints;

    drop table TB_CA_EVENTCONFIGS cascade constraints;

    drop table TB_CA_EVENTDETAILS cascade constraints;

    drop table TB_CA_EVENTEXTDETAILS cascade constraints;

    drop table TB_CA_EVENTHOLDINGANSWER cascade constraints;

    drop table TB_CA_EVENTMESSAGEFIELDS cascade constraints;

    drop table TB_CA_EVENTMESSAGES cascade constraints;

    drop table TB_CA_EVENTMRCONFIGS cascade constraints;

    drop table TB_CA_EVENTPROVIDERS cascade constraints;

    drop table TB_CA_EVENTS cascade constraints;

    drop table TB_CA_EVENTTYPEDETAILS cascade constraints;

    drop table TB_CA_EVENTTYPES cascade constraints;

    drop table TB_CA_FORMATPROVIDERS cascade constraints;

    drop table TB_CA_MESSAGEFIELDCONFIGS cascade constraints;

    drop table TB_CA_MESSAGEFORMATS cascade constraints;

    drop table TB_CA_MESSAGETYPES cascade constraints;

    drop table TB_CA_QUESTIONS cascade constraints;

    drop table TB_CONFIGS cascade constraints;

    drop table TB_CONTAINER cascade constraints;

    drop table TB_CONTAINER_SCHEDULE cascade constraints;

    drop table TB_CONTENT cascade constraints;

    drop table TB_CONTENTS_CATEGORIES cascade constraints;

    drop table TB_CONTENT_PERMISSION cascade constraints;

    drop table TB_CONTENT_STATUS cascade constraints;

    drop table TB_DIARY cascade constraints;

    drop table TB_DOCUMENTS cascade constraints;

    drop table TB_DOCUMENT_VERSIONS cascade constraints;

    drop table TB_DOMAINCLUSTERS cascade constraints;

    drop table TB_DOMAINNORMS cascade constraints;

    drop table TB_DOMAINS cascade constraints;

    drop table TB_DOMAINVALUES cascade constraints;

    drop table TB_FLOWS cascade constraints;

    drop table TB_FUNCTIONS cascade constraints;

    drop table TB_FUNCTION_GROUPS cascade constraints;

    drop table TB_GROUPS cascade constraints;

    drop table TB_GROUP_TYPES cascade constraints;

    drop table TB_HTML cascade constraints;

    drop table TB_LOGFIELDUPDATES cascade constraints;

    drop table TB_LOGS cascade constraints;

    drop table TB_MENU_ITEMS cascade constraints;

    drop table TB_MENU_ITEM_GROUP cascade constraints;

    drop table TB_MESSAGE cascade constraints;

    drop table TB_MIME_TYPES cascade constraints;

    drop table TB_MODULES cascade constraints;

    drop table TB_MODULE_TYPES cascade constraints;

    drop table TB_NEWS cascade constraints;

    drop table TB_NEWS_LINK cascade constraints;

    drop table TB_NEWS_PICTURE cascade constraints;

    drop table TB_OLD_PASSWORDS cascade constraints;

    drop table TB_PAGES cascade constraints;

    drop table TB_POLL cascade constraints;

    drop table TB_POLL_USER cascade constraints;

    drop table TB_POPUP_SCHEDULE cascade constraints;

    drop table TB_RESOURCE_COLLECTION cascade constraints;

    drop table TB_SDM_ASSETDETAILS cascade constraints;

    drop table TB_SDM_ASSETDYNAMICDETAILS cascade constraints;

    drop table TB_SDM_ASSETMESSAGEFIELDS cascade constraints;

    drop table TB_SDM_ASSETMESSAGES cascade constraints;

    drop table TB_SDM_ASSETS cascade constraints;

    drop table TB_SDM_ASSETSDYNAMIC cascade constraints;

    drop table TB_SDM_ASSETSSTATIC cascade constraints;

    drop table TB_SDM_ASSETSTATICDETAILS cascade constraints;

    drop table TB_SDM_ASSETTYPES cascade constraints;

    drop table TB_SDM_ASSETTYPE_DETAILS cascade constraints;

    drop table TB_SDM_ASSETTYPE_DETAILS_BLK cascade constraints;

    drop table TB_SDM_MESSAGEFIELDCONFIGS cascade constraints;

    drop table TB_SDM_MESSAGETYPES cascade constraints;

    drop table TB_SDM_PROVIDERS cascade constraints;

    drop table TB_SDM_STCONFIGFIELDS cascade constraints;

    drop table TB_SDM_STCONFIGMSGFLDS cascade constraints;

    drop table TB_SDM_STCONFIGS cascade constraints;

    drop table TB_SDM_STMRCONFIGS cascade constraints;

    drop table TB_SECURITY_CONSTRAINT cascade constraints;

    drop table TB_SP_CUSTODIAN_ACCOUNTS cascade constraints;

    drop table TB_SP_CUSTOMERS cascade constraints;

    drop table TB_SP_MANAGERGROUPS cascade constraints;

    drop table TB_SP_MANAGERS cascade constraints;

    drop table TB_SP_MARKETS cascade constraints;

    drop table TB_SP_PLANNING_PROCESS cascade constraints;

    drop table TB_SP_PROCESS cascade constraints;

    drop table TB_SP_SECURITIES cascade constraints;

    drop table TB_SP_SECURITYDETAILS cascade constraints;

    drop table TB_SP_SECURITYPORTFOLIO cascade constraints;

    drop table TB_SP_SECURITY_FASSETS cascade constraints;

    drop table TB_SP_SECURITY_FASSETS_DETAILS cascade constraints;

    drop table TB_SP_SECURITY_PLANNING cascade constraints;

    drop table TB_STATES cascade constraints;

    drop table TB_TRACKING cascade constraints;

    drop table TB_TRANSITIONS cascade constraints;

    drop table TB_USER_GROUP cascade constraints;

    drop table TB_USER_PROFILES cascade constraints;

    drop table TB_USER_TYPE cascade constraints;

    drop sequence ASSET_GEN;

    drop sequence CAALERTS_GEN;

    drop sequence CAEVDETEXT_GEN;

    drop sequence CAEVENTCONF_GEN;

    drop sequence CAEVENTDETAIL_GEN;

    drop sequence CAEVENTHOLDINGANSWER_GEN;

    drop sequence CAEVENTMESS_GEN;

    drop sequence CAEVENTMRCONF_GEN;

    drop sequence CAEVENT_GEN;

    drop sequence CAEVFIELDCONF_GEN;

    drop sequence CAEVFLDMESSCONF_GEN;

    drop sequence CAEVMESSFLD_GEN;

    drop sequence CAEVTYPEDET_GEN;

    drop sequence CAFORMATPROV_GEN;

    drop sequence CAQUESTION_GEN;

    drop sequence CASPSECURITYDETAIL_GEN;

    drop sequence SEQ_ASSETDETAIL_GEN;

    drop sequence SEQ_ASSETDYNAMICDETAIL_GEN;

    drop sequence SEQ_ASSETDYNAMIC_GEN;

    drop sequence SEQ_ASSETMESSAGE_GEN;

    drop sequence SEQ_ASSETSTATICDETAIL_GEN;

    drop sequence SEQ_ASSETSTATIC_GEN;

    drop sequence SEQ_ASSETTYPE_DETAIL_BLK_GEN;

    drop sequence SEQ_ASSETTYPE_DETAIL_GEN;

    drop sequence SEQ_CONFIGFIELDS_GEN;

    drop sequence SEQ_STCONFIGMSGFLDS_GEN;

    drop sequence SEQ_STCONF_GEN;

    drop sequence SEQ_STMRCONF_GEN;

    drop sequence SEQ_TB_SDM_PROVIDER_GEN;

    drop sequence SPCUSTOMER_GEN;

    drop sequence SPMANAGERGROUP_GEN;

    drop sequence SPMANAGER_GEN;

    drop sequence SPMARKET_GEN;

    drop sequence SPPERSECURITYPLANIFICATION_GEN;

    drop sequence SPPLANIFICATION_GEN;

    drop sequence SPPROCESS_GEN;

    drop sequence SPPROVIDERACCOUNT_GEN;

    drop sequence SPSECFADET_GEN;

    drop sequence SPSECPORT_GEN;

    drop sequence SPSECURITY_GEN;

    drop sequence TBLOGFIELD_GEN;

    drop sequence TBLOG_GEN;

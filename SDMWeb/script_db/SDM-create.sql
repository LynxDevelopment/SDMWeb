
    create table HS_CA_EVENTDETAILS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        REFID number(19,0),
        BOOLEAN1 number(1,0),
        BOOLEAN2 number(1,0),
        BOOLEAN3 number(1,0),
        BOOLEAN4 number(1,0),
        BOOLEAN5 number(1,0),
        BOOLEAN6 number(1,0),
        BOOLEAN7 number(1,0),
        BOOLEAN8 number(1,0),
        BOOLEAN9 number(1,0),
        BOOLEAN10 number(1,0),
        LONG1 number(19,0),
        LONG2 number(19,0),
        LONG3 number(19,0),
        LONG4 number(19,0),
        LONG5 number(19,0),
        LONG6 number(19,0),
        LONG7 number(19,0),
        LONG8 number(19,0),
        LONG9 number(19,0),
        LONG10 number(19,0),
        DOUBLE1 double precision,
        DOUBLE2 double precision,
        DOUBLE3 double precision,
        DOUBLE4 double precision,
        DOUBLE5 double precision,
        DOUBLE6 double precision,
        DOUBLE7 double precision,
        DOUBLE8 double precision,
        DOUBLE9 double precision,
        DOUBLE10 double precision,
        TIMESTAMP1 number(19,0),
        TIMESTAMP2 number(19,0),
        TIMESTAMP3 number(19,0),
        TIMESTAMP4 number(19,0),
        TIMESTAMP5 number(19,0),
        TIMESTAMP6 number(19,0),
        TIMESTAMP7 number(19,0),
        TIMESTAMP8 number(19,0),
        TIMESTAMP9 number(19,0),
        TIMESTAMP10 number(19,0),
        SHORTSTRING1 varchar2(16 char),
        SHORTSTRING2 varchar2(16 char),
        SHORTSTRING3 varchar2(16 char),
        SHORTSTRING4 varchar2(16 char),
        SHORTSTRING5 varchar2(16 char),
        SHORTSTRING6 varchar2(16 char),
        SHORTSTRING7 varchar2(16 char),
        SHORTSTRING8 varchar2(16 char),
        SHORTSTRING9 varchar2(16 char),
        SHORTSTRING10 varchar2(16 char),
        MIDDLESTRING1 varchar2(64 char),
        MIDDLESTRING2 varchar2(64 char),
        MIDDLESTRING3 varchar2(64 char),
        MIDDLESTRING4 varchar2(64 char),
        MIDDLESTRING5 varchar2(64 char),
        MIDDLESTRING6 varchar2(64 char),
        MIDDLESTRING7 varchar2(64 char),
        MIDDLESTRING8 varchar2(64 char),
        MIDDLESTRING9 varchar2(64 char),
        MIDDLESTRING10 varchar2(64 char),
        LONGSTRING1 varchar2(128 char),
        LONGSTRING2 varchar2(128 char),
        LONGSTRING3 varchar2(128 char),
        LONGSTRING4 varchar2(128 char),
        LONGSTRING5 varchar2(128 char),
        LONGSTRING6 varchar2(128 char),
        LONGSTRING7 varchar2(128 char),
        LONGSTRING8 varchar2(128 char),
        LONGSTRING9 varchar2(128 char),
        LONGSTRING10 varchar2(128 char),
        VERYLONGSTRING1 varchar2(256 char),
        VERYLONGSTRING2 varchar2(256 char),
        VERYLONGSTRING3 varchar2(256 char),
        VERYLONGSTRING4 varchar2(256 char),
        VERYLONGSTRING5 varchar2(256 char),
        VERYLONGSTRING6 varchar2(256 char),
        VERYLONGSTRING7 varchar2(256 char),
        VERYLONGSTRING8 varchar2(256 char),
        VERYLONGSTRING9 varchar2(256 char),
        VERYLONGSTRING10 varchar2(256 char),
        EXECUTIONDATE date,
        EXPIRATIONDATE date,
        OPERATIONALDATE date,
        SUSCRIPTIONDATE date,
        VERSION number(10,0) not null,
        primary key (ID)
    );

    create table HS_CA_EVENTEXTDETAILS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        REFID number(19,0),
        BOOLEAN1 number(1,0),
        BOOLEAN2 number(1,0),
        BOOLEAN3 number(1,0),
        BOOLEAN4 number(1,0),
        BOOLEAN5 number(1,0),
        BOOLEAN6 number(1,0),
        BOOLEAN7 number(1,0),
        BOOLEAN8 number(1,0),
        BOOLEAN9 number(1,0),
        BOOLEAN10 number(1,0),
        LONG1 number(19,0),
        LONG2 number(19,0),
        LONG3 number(19,0),
        LONG4 number(19,0),
        LONG5 number(19,0),
        LONG6 number(19,0),
        LONG7 number(19,0),
        LONG8 number(19,0),
        LONG9 number(19,0),
        LONG10 number(19,0),
        DOUBLE1 double precision,
        DOUBLE2 double precision,
        DOUBLE3 double precision,
        DOUBLE4 double precision,
        DOUBLE5 double precision,
        DOUBLE6 double precision,
        DOUBLE7 double precision,
        DOUBLE8 double precision,
        DOUBLE9 double precision,
        DOUBLE10 double precision,
        TIMESTAMP1 number(19,0),
        TIMESTAMP2 number(19,0),
        TIMESTAMP3 number(19,0),
        TIMESTAMP4 number(19,0),
        TIMESTAMP5 number(19,0),
        TIMESTAMP6 number(19,0),
        TIMESTAMP7 number(19,0),
        TIMESTAMP8 number(19,0),
        TIMESTAMP9 number(19,0),
        TIMESTAMP10 number(19,0),
        SHORTSTRING1 varchar2(16 char),
        SHORTSTRING2 varchar2(16 char),
        SHORTSTRING3 varchar2(16 char),
        SHORTSTRING4 varchar2(16 char),
        SHORTSTRING5 varchar2(16 char),
        SHORTSTRING6 varchar2(16 char),
        SHORTSTRING7 varchar2(16 char),
        SHORTSTRING8 varchar2(16 char),
        SHORTSTRING9 varchar2(16 char),
        SHORTSTRING10 varchar2(16 char),
        MIDDLESTRING1 varchar2(64 char),
        MIDDLESTRING2 varchar2(64 char),
        MIDDLESTRING3 varchar2(64 char),
        MIDDLESTRING4 varchar2(64 char),
        MIDDLESTRING5 varchar2(64 char),
        MIDDLESTRING6 varchar2(64 char),
        MIDDLESTRING7 varchar2(64 char),
        MIDDLESTRING8 varchar2(64 char),
        MIDDLESTRING9 varchar2(64 char),
        MIDDLESTRING10 varchar2(64 char),
        LONGSTRING1 varchar2(128 char),
        LONGSTRING2 varchar2(128 char),
        LONGSTRING3 varchar2(128 char),
        LONGSTRING4 varchar2(128 char),
        LONGSTRING5 varchar2(128 char),
        LONGSTRING6 varchar2(128 char),
        LONGSTRING7 varchar2(128 char),
        LONGSTRING8 varchar2(128 char),
        LONGSTRING9 varchar2(128 char),
        LONGSTRING10 varchar2(128 char),
        VERYLONGSTRING1 varchar2(256 char),
        VERYLONGSTRING2 varchar2(256 char),
        VERYLONGSTRING3 varchar2(256 char),
        VERYLONGSTRING4 varchar2(256 char),
        VERYLONGSTRING5 varchar2(256 char),
        VERYLONGSTRING6 varchar2(256 char),
        VERYLONGSTRING7 varchar2(256 char),
        VERYLONGSTRING8 varchar2(256 char),
        VERYLONGSTRING9 varchar2(256 char),
        VERYLONGSTRING10 varchar2(256 char),
        EXTENSIONNAME varchar2(16 char) not null,
        EXTENSIONNUMBER number(10,0) not null,
        EXTENSIONIDCODE varchar2(255 char),
        NARRATIVE clob,
        VERSION number(10,0) not null,
        FKEVENTDETAIL number(19,0) not null,
        primary key (ID),
        unique (FKEVENTDETAIL, EXTENSIONNAME, EXTENSIONNUMBER, VERSION)
    );

    create table HS_CA_EVENTMESSAGEFIELDS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        PATH varchar2(128 char) not null,
        FIELDTYPE number(10,0) not null,
        valueClob clob,
        VALUELONG varchar2(128 char),
        VALUESHORT varchar2(16 char),
        VALUEVERYLONG varchar2(256 char),
        FKHSEVENTMESSAGE number(19,0) not null,
        primary key (ID)
    );

    create table HS_CA_EVENTMESSAGES (
        ID number(19,0) not null,
        ACCOUNT varchar2(64 char),
        AMOUNT number(19,0),
        ANNOUNCEDATE date,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        EFFECTIVEDATE date,
        EVENTMESSGREF varchar2(64 char),
        EVENTREFERENCE varchar2(64 char),
        EVENTTYPE varchar2(64 char),
        ISEXTENSION number(1,0),
        EXTENSIONREF varchar2(64 char),
        ISINPORTFOLIO number(1,0),
        ISMAINMARKET number(1,0),
        MESSAGEID varchar2(128 char),
        FKEVENT number(19,0),
        OPERATIONNORM varchar2(6 char),
        OPERATION varchar2(64 char),
        ISENDED number(1,0) not null,
        ISTRANSMESSERROR number(1,0) not null,
        TRANSMESSKEY varchar2(128 char),
        TRANSMESSPARM varchar2(256 char),
        ORIGINID varchar2(16 char),
        ORIGINNAME varchar2(512 char),
        ORIGINPOSITION number(10,0),
        ORIGINTYPE varchar2(64 char),
        ORIGINALMESSAGE clob,
        PREVEVENTMESSGREF varchar2(64 char),
        RECEIVER varchar2(64 char),
        SECURITY varchar2(64 char),
        SECURITYNAME varchar2(128 char),
        SECURITYTYPE varchar2(64 char),
        SENDER varchar2(64 char),
        SENDERTIMESTAMP timestamp,
        VERSION number(10,0) not null,
        FKPROVIDER varchar2(16 char),
        FKTYPE varchar2(16 char),
        FKFORMAT varchar2(16 char),
        FKEVENTTYPE varchar2(4 char),
        FKSECURITY number(19,0),
        FKSTATE varchar2(16 char) not null,
        FKSTATEFLOW varchar2(16 char) not null,
        primary key (ID)
    );

    create table HS_CA_EVENTS (
        EVENTCLASS varchar2(32 char) not null,
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        REFID number(19,0),
        EXECUTIONDATE date,
        EXPIRATIONDATE date,
        ISMANDATORY number(1,0) not null,
        ISENDED number(1,0) not null,
        ISTRANSMESSERROR number(1,0) not null,
        TRANSMESSKEY varchar2(128 char),
        TRANSMESSPARM varchar2(256 char),
        OPERATIONALDATE date,
        ISPROVCANCELLED number(1,0) not null,
        ISPROVUPDATED number(1,0) not null,
        SUBSCRIPTIONDATE date,
        VERSION number(10,0) not null,
        ISCOMPLETE number(1,0),
        ENTITYOPERATIONALDATE date,
        FKEVENTTYPE varchar2(4 char) not null,
        FKSTATE varchar2(16 char) not null,
        FKSTATEFLOW varchar2(16 char) not null,
        FKSECURITY number(19,0),
        FKEVENTDETAIL number(19,0),
        FKEVENTGROUP number(19,0),
        FKEVENTPROVIDER varchar2(16 char),
        FKMASTEREVENT number(19,0),
        primary key (ID)
    );

    create table HS_SP_SECURITYPORTFOLIO (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        REFID number(19,0),
        CUSTODIANAMOUNT number(19,0),
        CUSTOMERAMOUNT number(19,0),
        EMISSIONDATE date not null,
        VERSION number(10,0) not null,
        FKCUSTOMER number(19,0) not null,
        FKSECURITY number(19,0) not null,
        primary key (ID)
    );

    create table SDM_PROCESSES (
        PROCESSCLASS char(1 char) not null,
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        CLASSIMPL varchar2(512 char) not null,
        DESCRIPTION varchar2(256 char) not null,
        NAME varchar2(128 char) not null,
        TYPE varchar2(40 char) not null,
        VERSION number(10,0) not null,
        primary key (ID),
        unique (PROCESSCLASS, NAME, ISDELETED)
    );

    create table SDM_PROCESSPROPERTIES (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        CODE varchar2(250 char) not null,
        VALUE clob,
        VERSION number(10,0) not null,
        FKPROCESS number(19,0),
        primary key (ID)
    );

    create table SDM_TASKEXECUTIONS (
        ID number(19,0) not null,
        ACTUALREFERENCEPROGRESS number(19,0),
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        EXECUTIONNAME varchar2(512 char),
        ISENDED number(1,0) not null,
        ISTRANSMESSERROR number(1,0) not null,
        TRANSMESSKEY varchar2(128 char),
        TRANSMESSPARM varchar2(256 char),
        PROGRESS number(19,0),
        PROGRESSSTATUS varchar2(4000 char),
        ISSCHEDULLED number(1,0) not null,
        TOTALREFERENCEPROGRESS number(19,0),
        VERSION number(10,0) not null,
        FKSTATE varchar2(16 char) not null,
        FKSTATEFLOW varchar2(16 char) not null,
        FKTASK number(19,0),
        primary key (ID)
    );

    create table SDM_TASKS (
        TASKCLASS char(1 char) not null,
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        ISENDED number(1,0) not null,
        ISTRANSMESSERROR number(1,0) not null,
        TRANSMESSKEY varchar2(128 char),
        TRANSMESSPARM varchar2(256 char),
        VERSION number(10,0) not null,
        CRONEXPRESSION varchar2(255 char),
        PLANNING_MODE varchar2(255 char),
        FKSTATE varchar2(16 char) not null,
        FKSTATEFLOW varchar2(16 char) not null,
        FKPROCESS number(19,0),
        primary key (ID)
    );

    create table TB_ANSWERS (
        POLL_ID number(10,0) not null,
        ANSWER_TEXT varchar2(200 char),
        HIT number(19,0),
        INITIAL_HIT number(19,0),
        ANSWERS_ID number(10,0) not null,
        primary key (POLL_ID, ANSWERS_ID)
    );

    create table TB_APPLICATIONS (
        ID varchar2(16 char) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        DATABASEDATE date,
        DATABASEVERSION number(10,6),
        DESCRIPTION varchar2(128 char),
        NAME varchar2(64 char) not null,
        SOFTWAREDATE date,
        SOFTWAREVERSION number(10,6),
        version number(10,0) not null,
        primary key (ID)
    );

    create table TB_CATEGORIES (
        CATEGORY_ID number(10,0) not null,
        CATEGORY_NAME varchar2(250 char),
        DESCRIPTION varchar2(250 char),
        CHILD_INDEX number(10,0),
        ENABLED number(1,0),
        MARKED number(1,0),
        ICON1 varchar2(250 char),
        ICON2 varchar2(250 char),
        PARENT_ID number(10,0),
        MASTER_CONTENT_ID number(10,0),
        CONTENT_LANGUAGE varchar2(255 char),
        primary key (CATEGORY_ID)
    );

    create table TB_CATEGORY_PERMISSION (
        CATEGORY_ID number(10,0) not null,
        PRINCIPAL varchar2(50 char),
        PRINCIPAL_TYPE varchar2(20 char),
        READ_PERMISSION number(1,0),
        WRITE_PERMISSION number(1,0)
    );

    create table TB_CA_ALERTS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        DESCRIPTION varchar2(256 char) not null,
        GRAPH varchar2(1 char) not null,
        LABEL varchar2(256 char) not null,
        LINK varchar2(128 char),
        QUERY varchar2(256 char) not null,
        USER_ROLE varchar2(2 char) not null,
        VERSION number(10,0) not null,
        primary key (ID)
    );

    create table TB_CA_ANSWERTYPES (
        ID varchar2(16 char) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        DESCRIPTION varchar2(256 char),
        NAME varchar2(64 char) not null,
        primary key (ID)
    );

    create table TB_CA_DOMAINCLUSTERFORMATS (
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        FKMESSAGEFORMAT varchar2(16 char) not null,
        FKDOMAINCLUSTER varchar2(32 char) not null,
        FKAPPLICATION varchar2(16 char) not null,
        FKDOMAIN varchar2(16 char) not null,
        primary key (FKDOMAINCLUSTER, FKAPPLICATION, FKDOMAIN, FKMESSAGEFORMAT)
    );

    create table TB_CA_EVENTCONFIGFIELDS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        ISREQUIRED number(1,0) not null,
        VERSION number(10,0) not null,
        FKEVENTCONFIG number(19,0) not null,
        FKEVENTTYPEDETAIL number(19,0) not null,
        primary key (ID),
        unique (FKEVENTCONFIG, FKEVENTTYPEDETAIL)
    );

    create table TB_CA_EVENTCONFIGMSGFLDS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        NORMSCRIPT varchar2(2048 char),
        VERSION number(10,0) not null,
        FKEVENTFIELDCONFIG number(19,0) not null,
        FKMSGTYPE varchar2(16 char) not null,
        FKFORMAT varchar2(16 char) not null,
        FKEVENTPROVIDER varchar2(16 char),
        primary key (ID),
        unique (FKEVENTFIELDCONFIG, FKFORMAT, FKMSGTYPE, FKEVENTPROVIDER)
    );

    create table TB_CA_EVENTCONFIGS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        CUSTMANNOTFOUND varchar2(32 char) not null,
        PRIMSECNOTFOUND varchar2(32 char) not null,
        VERSION number(10,0) not null,
        FKEVENTTYPE varchar2(4 char) not null,
        FKPREFPROVIDER1 varchar2(16 char) not null,
        FKSECPREFPROV2 varchar2(16 char),
        primary key (ID),
        unique (FKEVENTTYPE)
    );

    create table TB_CA_EVENTDETAILS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        REFID number(19,0),
        BOOLEAN1 number(1,0),
        BOOLEAN2 number(1,0),
        BOOLEAN3 number(1,0),
        BOOLEAN4 number(1,0),
        BOOLEAN5 number(1,0),
        BOOLEAN6 number(1,0),
        BOOLEAN7 number(1,0),
        BOOLEAN8 number(1,0),
        BOOLEAN9 number(1,0),
        BOOLEAN10 number(1,0),
        LONG1 number(19,0),
        LONG2 number(19,0),
        LONG3 number(19,0),
        LONG4 number(19,0),
        LONG5 number(19,0),
        LONG6 number(19,0),
        LONG7 number(19,0),
        LONG8 number(19,0),
        LONG9 number(19,0),
        LONG10 number(19,0),
        DOUBLE1 double precision,
        DOUBLE2 double precision,
        DOUBLE3 double precision,
        DOUBLE4 double precision,
        DOUBLE5 double precision,
        DOUBLE6 double precision,
        DOUBLE7 double precision,
        DOUBLE8 double precision,
        DOUBLE9 double precision,
        DOUBLE10 double precision,
        TIMESTAMP1 number(19,0),
        TIMESTAMP2 number(19,0),
        TIMESTAMP3 number(19,0),
        TIMESTAMP4 number(19,0),
        TIMESTAMP5 number(19,0),
        TIMESTAMP6 number(19,0),
        TIMESTAMP7 number(19,0),
        TIMESTAMP8 number(19,0),
        TIMESTAMP9 number(19,0),
        TIMESTAMP10 number(19,0),
        SHORTSTRING1 varchar2(16 char),
        SHORTSTRING2 varchar2(16 char),
        SHORTSTRING3 varchar2(16 char),
        SHORTSTRING4 varchar2(16 char),
        SHORTSTRING5 varchar2(16 char),
        SHORTSTRING6 varchar2(16 char),
        SHORTSTRING7 varchar2(16 char),
        SHORTSTRING8 varchar2(16 char),
        SHORTSTRING9 varchar2(16 char),
        SHORTSTRING10 varchar2(16 char),
        MIDDLESTRING1 varchar2(64 char),
        MIDDLESTRING2 varchar2(64 char),
        MIDDLESTRING3 varchar2(64 char),
        MIDDLESTRING4 varchar2(64 char),
        MIDDLESTRING5 varchar2(64 char),
        MIDDLESTRING6 varchar2(64 char),
        MIDDLESTRING7 varchar2(64 char),
        MIDDLESTRING8 varchar2(64 char),
        MIDDLESTRING9 varchar2(64 char),
        MIDDLESTRING10 varchar2(64 char),
        LONGSTRING1 varchar2(128 char),
        LONGSTRING2 varchar2(128 char),
        LONGSTRING3 varchar2(128 char),
        LONGSTRING4 varchar2(128 char),
        LONGSTRING5 varchar2(128 char),
        LONGSTRING6 varchar2(128 char),
        LONGSTRING7 varchar2(128 char),
        LONGSTRING8 varchar2(128 char),
        LONGSTRING9 varchar2(128 char),
        LONGSTRING10 varchar2(128 char),
        VERYLONGSTRING1 varchar2(256 char),
        VERYLONGSTRING2 varchar2(256 char),
        VERYLONGSTRING3 varchar2(256 char),
        VERYLONGSTRING4 varchar2(256 char),
        VERYLONGSTRING5 varchar2(256 char),
        VERYLONGSTRING6 varchar2(256 char),
        VERYLONGSTRING7 varchar2(256 char),
        VERYLONGSTRING8 varchar2(256 char),
        VERYLONGSTRING9 varchar2(256 char),
        VERYLONGSTRING10 varchar2(256 char),
        EXECUTIONDATE date,
        EXPIRATIONDATE date,
        OPERATIONALDATE date,
        SUSCRIPTIONDATE date,
        VERSION number(10,0) not null,
        primary key (ID)
    );

    create table TB_CA_EVENTEXTDETAILS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        REFID number(19,0),
        BOOLEAN1 number(1,0),
        BOOLEAN2 number(1,0),
        BOOLEAN3 number(1,0),
        BOOLEAN4 number(1,0),
        BOOLEAN5 number(1,0),
        BOOLEAN6 number(1,0),
        BOOLEAN7 number(1,0),
        BOOLEAN8 number(1,0),
        BOOLEAN9 number(1,0),
        BOOLEAN10 number(1,0),
        LONG1 number(19,0),
        LONG2 number(19,0),
        LONG3 number(19,0),
        LONG4 number(19,0),
        LONG5 number(19,0),
        LONG6 number(19,0),
        LONG7 number(19,0),
        LONG8 number(19,0),
        LONG9 number(19,0),
        LONG10 number(19,0),
        DOUBLE1 double precision,
        DOUBLE2 double precision,
        DOUBLE3 double precision,
        DOUBLE4 double precision,
        DOUBLE5 double precision,
        DOUBLE6 double precision,
        DOUBLE7 double precision,
        DOUBLE8 double precision,
        DOUBLE9 double precision,
        DOUBLE10 double precision,
        TIMESTAMP1 number(19,0),
        TIMESTAMP2 number(19,0),
        TIMESTAMP3 number(19,0),
        TIMESTAMP4 number(19,0),
        TIMESTAMP5 number(19,0),
        TIMESTAMP6 number(19,0),
        TIMESTAMP7 number(19,0),
        TIMESTAMP8 number(19,0),
        TIMESTAMP9 number(19,0),
        TIMESTAMP10 number(19,0),
        SHORTSTRING1 varchar2(16 char),
        SHORTSTRING2 varchar2(16 char),
        SHORTSTRING3 varchar2(16 char),
        SHORTSTRING4 varchar2(16 char),
        SHORTSTRING5 varchar2(16 char),
        SHORTSTRING6 varchar2(16 char),
        SHORTSTRING7 varchar2(16 char),
        SHORTSTRING8 varchar2(16 char),
        SHORTSTRING9 varchar2(16 char),
        SHORTSTRING10 varchar2(16 char),
        MIDDLESTRING1 varchar2(64 char),
        MIDDLESTRING2 varchar2(64 char),
        MIDDLESTRING3 varchar2(64 char),
        MIDDLESTRING4 varchar2(64 char),
        MIDDLESTRING5 varchar2(64 char),
        MIDDLESTRING6 varchar2(64 char),
        MIDDLESTRING7 varchar2(64 char),
        MIDDLESTRING8 varchar2(64 char),
        MIDDLESTRING9 varchar2(64 char),
        MIDDLESTRING10 varchar2(64 char),
        LONGSTRING1 varchar2(128 char),
        LONGSTRING2 varchar2(128 char),
        LONGSTRING3 varchar2(128 char),
        LONGSTRING4 varchar2(128 char),
        LONGSTRING5 varchar2(128 char),
        LONGSTRING6 varchar2(128 char),
        LONGSTRING7 varchar2(128 char),
        LONGSTRING8 varchar2(128 char),
        LONGSTRING9 varchar2(128 char),
        LONGSTRING10 varchar2(128 char),
        VERYLONGSTRING1 varchar2(256 char),
        VERYLONGSTRING2 varchar2(256 char),
        VERYLONGSTRING3 varchar2(256 char),
        VERYLONGSTRING4 varchar2(256 char),
        VERYLONGSTRING5 varchar2(256 char),
        VERYLONGSTRING6 varchar2(256 char),
        VERYLONGSTRING7 varchar2(256 char),
        VERYLONGSTRING8 varchar2(256 char),
        VERYLONGSTRING9 varchar2(256 char),
        VERYLONGSTRING10 varchar2(256 char),
        EXTENSIONNAME varchar2(16 char) not null,
        EXTENSIONNUMBER number(10,0) not null,
        EXTENSIONIDCODE varchar2(255 char),
        NARRATIVE clob,
        VERSION number(10,0) not null,
        FKEVENTDETAIL number(19,0) not null,
        primary key (ID),
        unique (FKEVENTDETAIL, EXTENSIONNAME, EXTENSIONNUMBER, VERSION)
    );

    create table TB_CA_EVENTHOLDINGANSWER (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        BOOLEAN1 number(1,0),
        BOOLEAN2 number(1,0),
        BOOLEAN3 number(1,0),
        BOOLEAN4 number(1,0),
        BOOLEAN5 number(1,0),
        BOOLEAN6 number(1,0),
        BOOLEAN7 number(1,0),
        BOOLEAN8 number(1,0),
        BOOLEAN9 number(1,0),
        BOOLEAN10 number(1,0),
        LONG1 number(19,0),
        LONG2 number(19,0),
        LONG3 number(19,0),
        LONG4 number(19,0),
        LONG5 number(19,0),
        LONG6 number(19,0),
        LONG7 number(19,0),
        LONG8 number(19,0),
        LONG9 number(19,0),
        LONG10 number(19,0),
        DOUBLE1 double precision,
        DOUBLE2 double precision,
        DOUBLE3 double precision,
        DOUBLE4 double precision,
        DOUBLE5 double precision,
        DOUBLE6 double precision,
        DOUBLE7 double precision,
        DOUBLE8 double precision,
        DOUBLE9 double precision,
        DOUBLE10 double precision,
        TIMESTAMP1 number(19,0),
        TIMESTAMP2 number(19,0),
        TIMESTAMP3 number(19,0),
        TIMESTAMP4 number(19,0),
        TIMESTAMP5 number(19,0),
        TIMESTAMP6 number(19,0),
        TIMESTAMP7 number(19,0),
        TIMESTAMP8 number(19,0),
        TIMESTAMP9 number(19,0),
        TIMESTAMP10 number(19,0),
        SHORTSTRING1 varchar2(16 char),
        SHORTSTRING2 varchar2(16 char),
        SHORTSTRING3 varchar2(16 char),
        SHORTSTRING4 varchar2(16 char),
        SHORTSTRING5 varchar2(16 char),
        SHORTSTRING6 varchar2(16 char),
        SHORTSTRING7 varchar2(16 char),
        SHORTSTRING8 varchar2(16 char),
        SHORTSTRING9 varchar2(16 char),
        SHORTSTRING10 varchar2(16 char),
        MIDDLESTRING1 varchar2(64 char),
        MIDDLESTRING2 varchar2(64 char),
        MIDDLESTRING3 varchar2(64 char),
        MIDDLESTRING4 varchar2(64 char),
        MIDDLESTRING5 varchar2(64 char),
        MIDDLESTRING6 varchar2(64 char),
        MIDDLESTRING7 varchar2(64 char),
        MIDDLESTRING8 varchar2(64 char),
        MIDDLESTRING9 varchar2(64 char),
        MIDDLESTRING10 varchar2(64 char),
        LONGSTRING1 varchar2(128 char),
        LONGSTRING2 varchar2(128 char),
        LONGSTRING3 varchar2(128 char),
        LONGSTRING4 varchar2(128 char),
        LONGSTRING5 varchar2(128 char),
        LONGSTRING6 varchar2(128 char),
        LONGSTRING7 varchar2(128 char),
        LONGSTRING8 varchar2(128 char),
        LONGSTRING9 varchar2(128 char),
        LONGSTRING10 varchar2(128 char),
        CHAR1 varchar2(1 char),
        CHAR2 varchar2(1 char),
        CHAR3 varchar2(1 char),
        CHAR4 varchar2(1 char),
        CHAR5 varchar2(1 char),
        CHAR6 varchar2(1 char),
        CHAR7 varchar2(1 char),
        CHAR8 varchar2(1 char),
        CHAR9 varchar2(1 char),
        CHAR10 varchar2(1 char),
        VERYLONGSTRING1 char(1 char),
        VERYLONGSTRING2 char(1 char),
        VERYLONGSTRING3 char(1 char),
        VERYLONGSTRING4 char(1 char),
        VERYLONGSTRING5 char(1 char),
        VERYLONGSTRING6 char(1 char),
        VERYLONGSTRING7 char(1 char),
        VERYLONGSTRING8 char(1 char),
        VERYLONGSTRING9 char(1 char),
        VERYLONGSTRING10 char(1 char),
        VERSION number(10,0) not null,
        FKHOLDING number(19,0) not null,
        FKQUESTION number(19,0) not null,
        primary key (ID)
    );

    create table TB_CA_EVENTMESSAGEFIELDS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        PATH varchar2(128 char) not null,
        FIELDTYPE number(10,0) not null,
        valueClob clob,
        VALUELONG varchar2(128 char),
        VALUESHORT varchar2(16 char),
        VALUEVERYLONG varchar2(256 char),
        FKEVENTMESSAGE number(19,0) not null,
        primary key (ID)
    );

    create table TB_CA_EVENTMESSAGES (
        ID number(19,0) not null,
        ACCOUNT varchar2(64 char),
        AMOUNT number(19,0),
        ANNOUNCEDATE date,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        EFFECTIVEDATE date,
        EVENTMESSGREF varchar2(64 char),
        EVENTREFERENCE varchar2(64 char),
        EVENTTYPE varchar2(64 char),
        ISEXTENSION number(1,0),
        EXTENSIONREF varchar2(64 char),
        ISINPORTFOLIO number(1,0),
        ISMAINMARKET number(1,0),
        MESSAGEID varchar2(128 char),
        FKEVENT number(19,0),
        OPERATIONNORM varchar2(6 char),
        OPERATION varchar2(64 char),
        ISENDED number(1,0) not null,
        ISTRANSMESSERROR number(1,0) not null,
        TRANSMESSKEY varchar2(128 char),
        TRANSMESSPARM varchar2(256 char),
        ORIGINID varchar2(16 char),
        ORIGINNAME varchar2(512 char),
        ORIGINPOSITION number(10,0),
        ORIGINTYPE varchar2(64 char),
        ORIGINALMESSAGE clob,
        PREVEVENTMESSGREF varchar2(64 char),
        RECEIVER varchar2(64 char),
        SECURITY varchar2(64 char),
        SECURITYNAME varchar2(128 char),
        SECURITYTYPE varchar2(64 char),
        SENDER varchar2(64 char),
        SENDERTIMESTAMP timestamp,
        VERSION number(10,0) not null,
        FKPROVIDER varchar2(16 char),
        FKTYPE varchar2(16 char),
        FKFORMAT varchar2(16 char),
        FKEVENTTYPE varchar2(4 char),
        FKSECURITY number(19,0),
        FKSTATE varchar2(16 char) not null,
        FKSTATEFLOW varchar2(16 char) not null,
        primary key (ID)
    );

    create table TB_CA_EVENTMRCONFIGS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        CONDITION varchar2(32 char),
        CONDITIONPARAMS varchar2(128 char),
        ONFALSERESULT varchar2(32 char),
        ONTRUERESULT varchar2(32 char),
        RULEORDER number(10,0),
        TARGET varchar2(64 char),
        FKEVENTTYPEDETAIL number(19,0) not null,
        FKEVENTCONFIG number(19,0) not null,
        primary key (ID),
        unique (FKEVENTCONFIG, RULEORDER)
    );

    create table TB_CA_EVENTPROVIDERS (
        EVENTPROVCLASS varchar2(16 char) not null,
        ID varchar2(16 char) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        NAME varchar2(64 char) not null,
        VERSION number(10,0) not null,
        primary key (ID)
    );

    create table TB_CA_EVENTS (
        EVENTCLASS varchar2(32 char) not null,
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        REFID number(19,0),
        EXECUTIONDATE date,
        EXPIRATIONDATE date,
        ISMANDATORY number(1,0) not null,
        ISENDED number(1,0) not null,
        ISTRANSMESSERROR number(1,0) not null,
        TRANSMESSKEY varchar2(128 char),
        TRANSMESSPARM varchar2(256 char),
        OPERATIONALDATE date,
        ISPROVCANCELLED number(1,0) not null,
        ISPROVUPDATED number(1,0) not null,
        SUBSCRIPTIONDATE date,
        VERSION number(10,0) not null,
        ISCOMPLETE number(1,0),
        ENTITYOPERATIONALDATE date,
        FKEVENTTYPE varchar2(4 char) not null,
        FKSTATE varchar2(16 char) not null,
        FKSTATEFLOW varchar2(16 char) not null,
        FKSECURITY number(19,0),
        FKEVENTDETAIL number(19,0),
        FKEVENTGROUP number(19,0),
        FKEVENTPROVIDER varchar2(16 char),
        FKMASTEREVENT number(19,0),
        primary key (ID)
    );

    create table TB_CA_EVENTTYPEDETAILS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        ISBASIC number(1,0) not null,
        DESCRIPTION varchar2(256 char),
        DISPLAYCOLUMN number(10,0),
        DISPLAYGROUP varchar2(64 char) not null,
        DISPLAYGROUPORDER number(10,0) not null,
        DISPLAYINGROUPORDER number(10,0),
        ISDISPLAYINTABLE number(1,0) not null,
        DISPLAYINTABLEORDER number(10,0) not null,
        DISPLAYLEFT number(10,0),
        DISPLAYROW number(10,0),
        DISPLAYTOP number(10,0),
        EDITABLENORMALIZATION number(1,0) not null,
        ISEXTENSION number(1,0) not null,
        FIELDPATH varchar2(32 char) not null,
        FIELDTYPE varchar2(16 char) not null,
        FORMAT varchar2(32 char),
        ISHIDDEN number(1,0) not null,
        MAXLENGTH number(10,0) not null,
        NAME varchar2(64 char) not null,
        VERSION number(10,0) not null,
        FKEVENTTYPE varchar2(4 char) not null,
        primary key (ID)
    );

    create table TB_CA_EVENTTYPES (
        ID varchar2(4 char) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        DESCRIPTION varchar2(256 char),
        NAME varchar2(64 char) not null,
        ISOPTIONAL number(1,0) not null,
        primary key (ID)
    );

    create table TB_CA_FORMATPROVIDERS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        IDATMESSAGE varchar2(16 char) not null,
        FKEVENTPROVIDER varchar2(16 char) not null,
        FKMESSAGEFORMAT varchar2(16 char) not null,
        primary key (ID)
    );

    create table TB_CA_MESSAGEFIELDCONFIGS (
        PATH varchar2(128 char) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        DESCRIPTION varchar2(140 char),
        DISPLAYCOLUMN number(10,0) not null,
        DISPLAYGROUP varchar2(64 char),
        DISPLAYROW number(10,0) not null,
        FIELDFORMAT varchar2(32 char),
        FIELDLENGTH number(10,0),
        FIELDNAME varchar2(64 char) not null,
        FIELDTYPE varchar2(32 char) not null,
        HIDDEN number(1,0) not null,
        VERSION number(10,0) not null,
        FKTYPE varchar2(16 char) not null,
        FKFORMAT varchar2(16 char) not null,
        primary key (PATH, FKTYPE, FKFORMAT)
    );

    create table TB_CA_MESSAGEFORMATS (
        ID varchar2(16 char) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        NAME varchar2(64 char) not null,
        PATH varchar2(128 char) not null,
        PATTERN varchar2(64 char) not null,
        VERSION number(10,0) not null,
        primary key (ID)
    );

    create table TB_CA_MESSAGETYPES (
        CODE varchar2(16 char) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        NAME varchar2(64 char) not null,
        FKFORMAT varchar2(16 char) not null,
        primary key (CODE, FKFORMAT)
    );

    create table TB_CA_QUESTIONS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        DEFAULTVALUE varchar2(256 char),
        DESCRIPTION varchar2(256 char),
        FORMAT varchar2(64 char),
        ISHEADER number(1,0) not null,
        HEADERGROUP number(19,0) not null,
        HEADERPOSITION number(10,0) not null,
        ISHIDDEN number(1,0) not null,
        ISOPTIONAL number(1,0) not null,
        POSITION number(10,0) not null,
        TEXT varchar2(256 char) not null,
        VERSION number(10,0) not null,
        FKANSWERTYPE varchar2(16 char),
        FKEVENT number(19,0) not null,
        primary key (ID)
    );

    create table TB_CONFIGS (
        ID varchar2(32 char) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        BOOLEANVALUE number(1,0),
        DESCRIPTION varchar2(128 char),
        DOUBLEVALUE double precision,
        ISEDITABLE number(1,0) not null,
        LONGSTRINGVALUE varchar2(128 char),
        LONGVALUE number(19,0),
        MIDDLESTRINGVALUE varchar2(64 char),
        SHORTSTRINGVALUE varchar2(16 char),
        TIMESTAMPVALUE timestamp,
        TYPEVALUE varchar2(15 char) not null,
        ISUPDATABLE number(1,0) not null,
        VERSION number(10,0) not null,
        VERYLONGSTRINGVALUE varchar2(256 char),
        FKAPPLICATION varchar2(16 char) not null,
        primary key (FKAPPLICATION, ID)
    );

    create table TB_CONTAINER (
        NAME varchar2(100 char) not null,
        TYPE varchar2(15 char) not null,
        primary key (NAME, TYPE)
    );

    create table TB_CONTAINER_SCHEDULE (
        SCHEDULE_ID number(10,0) not null,
        SCHEDULE_NAME varchar2(100 char),
        CONTAINER_NAME varchar2(100 char),
        CONTAINER_TYPE varchar2(15 char),
        PUBLISH_DATE timestamp,
        EXPIRATION_DATE timestamp,
        STATUS number(1,0),
        CONTENT_ID number(10,0),
        URL varchar2(200 char),
        ORDER_CONFIG number(10,0),
        primary key (SCHEDULE_ID)
    );

    create table TB_CONTENT (
        CONTENT_ID number(10,0) not null,
        STATUS_ID number(10,0),
        CREATION_DATE timestamp,
        RELEASE_DATE timestamp,
        DRAFT_DATE timestamp,
        APPROVAL_DATE timestamp,
        PUBLISH_DATE timestamp,
        EXPIRATION_DATE timestamp,
        DRAFT_USER_ID varchar2(20 char),
        RELEASE_USER_ID varchar2(20 char),
        APPROVE_USER_ID varchar2(20 char),
        MODULE_ID varchar2(20 char),
        CONTENT_LANGUAGE varchar2(2 char),
        MASTER_CONTENT_ID number(10,0),
        primary key (CONTENT_ID)
    );

    create table TB_CONTENTS_CATEGORIES (
        CONTENT_ID number(10,0) not null,
        CATEGORY_ID number(10,0) not null,
        primary key (CATEGORY_ID, CONTENT_ID)
    );

    create table TB_CONTENT_PERMISSION (
        CONTENT_ID number(10,0) not null,
        PRINCIPAL varchar2(50 char),
        PRINCIPAL_TYPE varchar2(20 char),
        READ_PERMISSION number(1,0),
        WRITE_PERMISSION number(1,0)
    );

    create table TB_CONTENT_STATUS (
        STATUS_ID number(10,0) not null,
        DESCRIPTION varchar2(20 char),
        primary key (STATUS_ID)
    );

    create table TB_DIARY (
        CONTENT_ID number(10,0) not null,
        SUBJECT varchar2(100 char),
        MESSAGE_DATE timestamp,
        MESSAGE clob,
        primary key (CONTENT_ID)
    );

    create table TB_DOCUMENTS (
        CONTENT_ID number(10,0) not null,
        FILE_NAME varchar2(100 char),
        SUBJECT varchar2(100 char),
        DESCRIPTION varchar2(1000 char),
        KEYWORDS varchar2(2000 char),
        primary key (CONTENT_ID)
    );

    create table TB_DOCUMENT_VERSIONS (
        CONTENT_ID number(10,0) not null,
        AUTHOR varchar2(100 char),
        VERSION_DATE timestamp,
        SAVED_FILE_NAME varchar2(256 char),
        MIME_TYPE varchar2(50 char),
        DOCUMENT_ID number(10,0),
        ATTACHED_TO number(10,0),
        VERSION_NUMBER number(10,0),
        primary key (CONTENT_ID)
    );

    create table TB_DOMAINCLUSTERS (
        CODE varchar2(32 char) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        NAME varchar2(64 char) not null,
        VERSION number(10,0) not null,
        FKAPPLICATION varchar2(16 char) not null,
        FKDOMAIN varchar2(16 char) not null,
        primary key (CODE, FKAPPLICATION, FKDOMAIN)
    );

    create table TB_DOMAINNORMS (
        CODE varchar2(32 char) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        DESCRIPTION varchar2(64 char),
        VERSION number(10,0) not null,
        FKAPPLICATION varchar2(16 char) not null,
        FKDOMAIN varchar2(16 char) not null,
        primary key (CODE, FKAPPLICATION, FKDOMAIN)
    );

    create table TB_DOMAINS (
        CODE varchar2(16 char) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        ISCACHEABLE number(1,0) not null,
        ISDELETABLE number(1,0) not null,
        NAME varchar2(64 char),
        ISUPDATABLE number(1,0) not null,
        VERSION number(10,0) not null,
        FKAPPLICATION varchar2(16 char) not null,
        primary key (FKAPPLICATION, CODE)
    );

    create table TB_DOMAINVALUES (
        ORIGINALVALUE varchar2(64 char) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        VERSION number(10,0) not null,
        FKNORM varchar2(32 char) not null,
        FKNRMAPPLICATION varchar2(16 char) not null,
        FKNRMDOMAIN varchar2(16 char) not null,
        FKCLUSTER varchar2(32 char) not null,
        FKCLTAPPLICATION varchar2(16 char) not null,
        FKCLTDOMAIN varchar2(16 char) not null,
        primary key (FKCLUSTER, FKCLTAPPLICATION, FKCLTDOMAIN, FKNORM, FKNRMAPPLICATION, FKNRMDOMAIN, ORIGINALVALUE)
    );

    create table TB_FLOWS (
        ID varchar2(16 char) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        DESCRIPTION varchar2(128 char),
        NAME varchar2(64 char) not null,
        FKAPPLICATION varchar2(16 char) not null,
        primary key (ID)
    );

    create table TB_FUNCTIONS (
        FUNCTION_ID varchar2(30 char) not null,
        MODULE_ID varchar2(30 char) not null,
        FUNCTION_URI varchar2(255 char),
        FUNCTION_TITLE varchar2(100 char),
        primary key (FUNCTION_ID, MODULE_ID)
    );

    create table TB_FUNCTION_GROUPS (
        GROUP_ID varchar2(30 char) not null,
        FUNCTION_ID varchar2(30 char) not null,
        MODULE_ID varchar2(30 char) not null,
        primary key (GROUP_ID, FUNCTION_ID, MODULE_ID)
    );

    create table TB_GROUPS (
        GROUP_ID varchar2(30 char) not null,
        GROUP_TYPE number(10,0) not null,
        DESCRIPTION varchar2(255 char),
        GROUP_RULE varchar2(255 char),
        primary key (GROUP_ID)
    );

    create table TB_GROUP_TYPES (
        GROUP_TYPE number(10,0) not null,
        DESCRIPTION varchar2(255 char),
        ADMIN_PAGE_URL varchar2(255 char),
        primary key (GROUP_TYPE)
    );

    create table TB_HTML (
        CONTENT_ID number(10,0) not null,
        TITLE varchar2(100 char),
        BODY clob,
        primary key (CONTENT_ID)
    );

    create table TB_LOGFIELDUPDATES (
        ID number(10,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        FIELDNAME varchar2(128 char) not null,
        NEWVALUE varchar2(256 char) not null,
        OLDVALUE varchar2(256 char) not null,
        FKLOG number(19,0) not null,
        primary key (ID)
    );

    create table TB_LOGS (
        LOGCLASS varchar2(32 char) not null,
        ID number(19,0) not null,
        FKAPPLICATION varchar2(16 char) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        LOGLEVEL number(10,0) not null,
        MESSAGE varchar2(1750 char) not null,
        LOGTYPE varchar2(128 char) not null,
        STACKTRACE clob,
        ENTITYNAME varchar2(255 char),
        OPERATIONID number(19,0),
        FKFLOW varchar2(16 char),
        FKTRANSITION varchar2(16 char),
        UPDATEDENTITY varchar2(128 char),
        primary key (ID)
    );

    create table TB_MENU_ITEMS (
        ITEM_TYPE varchar2(31 char) not null,
        MENU_ITEM_ID varchar2(255 char) not null,
        ENABLED char(1 char),
        ICON1 varchar2(255 char),
        ICON2 varchar2(255 char),
        LANGUAGE_ID varchar2(255 char),
        MARKED char(1 char),
        MENU_ITEM_TITLE varchar2(255 char),
        URL varchar2(255 char),
        EXTERNAL_LINK number(1,0),
        TARGET_LINK number(1,0),
        PARENT varchar2(255 char),
        CONTAINER_NAME varchar2(100 char),
        CONTAINER_TYPE varchar2(15 char),
        CATEGORY_ID number(10,0),
        CHILD_INDEX number(10,0),
        primary key (MENU_ITEM_ID)
    );

    create table TB_MENU_ITEM_GROUP (
        MENU_ITEM_ID varchar2(255 char) not null,
        GROUP_ID varchar2(30 char) not null,
        primary key (MENU_ITEM_ID, GROUP_ID)
    );

    create table TB_MESSAGE (
        CONTENT_ID number(10,0) not null,
        SUBJECT varchar2(100 char),
        MESSAGE_DATE timestamp,
        MESSAGE clob,
        primary key (CONTENT_ID)
    );

    create table TB_MIME_TYPES (
        MIME_TYPE varchar2(50 char) not null,
        MIME_DESCRIPTION varchar2(200 char),
        primary key (MIME_TYPE)
    );

    create table TB_MODULES (
        MODULE_ID varchar2(30 char) not null,
        ADMIN_URI varchar2(200 char),
        MODULE_TITLE varchar2(100 char),
        CONTENT_LOCATION varchar2(100 char),
        DESCRIPTION varchar2(200 char),
        ICON1 varchar2(250 char),
        ICON2 varchar2(250 char),
        MODULE_TYPE number(10,0),
        primary key (MODULE_ID)
    );

    create table TB_MODULE_TYPES (
        MODULE_TYPE number(10,0) not null,
        DESCRIPTION varchar2(200 char),
        primary key (MODULE_TYPE)
    );

    create table TB_NEWS (
        CONTENT_ID number(10,0) not null,
        TITLE varchar2(255 char),
        SUBTITLE varchar2(255 char),
        BODY clob,
        primary key (CONTENT_ID)
    );

    create table TB_NEWS_LINK (
        NEWS_ID number(10,0) not null,
        URL varchar2(255 char),
        NEWS_LINK_TITLE varchar2(255 char),
        ALT varchar2(255 char),
        LINK_ID number(10,0) not null,
        primary key (NEWS_ID, LINK_ID)
    );

    create table TB_NEWS_PICTURE (
        NEWS_ID number(10,0) not null,
        URL varchar2(255 char),
        ALT varchar2(255 char),
        PICTURE_NAME varchar2(255 char),
        PICTURE_ID number(10,0) not null,
        primary key (NEWS_ID, PICTURE_ID)
    );

    create table TB_OLD_PASSWORDS (
        USER_ID varchar2(30 char) not null,
        PASSWORD varchar2(30 char),
        PASSWORD_TIMESTAMP timestamp
    );

    create table TB_PAGES (
        SEQUENCE_NUMBER number(19,0) not null,
        TRACKING_ID number(19,0) not null,
        ACCESS_TIME timestamp,
        PAGE_NAME varchar2(80 char),
        REFERRER varchar2(80 char),
        primary key (SEQUENCE_NUMBER, TRACKING_ID)
    );

    create table TB_POLL (
        CONTENT_ID number(10,0) not null,
        DESCRIPTION varchar2(100 char),
        QUESTION varchar2(200 char),
        QUESTION_TYPE number(10,0),
        primary key (CONTENT_ID)
    );

    create table TB_POLL_USER (
        POLL_ID number(10,0) not null,
        USER_ID varchar2(30 char)
    );

    create table TB_POPUP_SCHEDULE (
        SCHEDULE_ID number(10,0) not null,
        HEIGHT number(19,0),
        WIDTH number(19,0),
        LEFT_POS number(19,0),
        TOP_POS number(19,0),
        TIMER number(19,0),
        VIEW_TIMES number(19,0),
        primary key (SCHEDULE_ID)
    );

    create table TB_RESOURCE_COLLECTION (
        COLLECTION_NAME varchar2(20 char) not null,
        DESCRIPTION varchar2(200 char),
        URL_PATTERN varchar2(200 char),
        CONSTRAINTS_ENABLED number(1,0),
        primary key (COLLECTION_NAME)
    );

    create table TB_SDM_ASSETDETAILS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        BOOLEAN1 number(1,0),
        BOOLEAN2 number(1,0),
        BOOLEAN3 number(1,0),
        BOOLEAN4 number(1,0),
        BOOLEAN5 number(1,0),
        BOOLEAN6 number(1,0),
        BOOLEAN7 number(1,0),
        BOOLEAN8 number(1,0),
        BOOLEAN9 number(1,0),
        BOOLEAN10 number(1,0),
        LONG1 number(19,0),
        LONG2 number(19,0),
        LONG3 number(19,0),
        LONG4 number(19,0),
        LONG5 number(19,0),
        LONG6 number(19,0),
        LONG7 number(19,0),
        LONG8 number(19,0),
        LONG9 number(19,0),
        LONG10 number(19,0),
        DOUBLE1 double precision,
        DOUBLE2 double precision,
        DOUBLE3 double precision,
        DOUBLE4 double precision,
        DOUBLE5 double precision,
        DOUBLE6 double precision,
        DOUBLE7 double precision,
        DOUBLE8 double precision,
        DOUBLE9 double precision,
        DOUBLE10 double precision,
        TIMESTAMP1 number(19,0),
        TIMESTAMP2 number(19,0),
        TIMESTAMP3 number(19,0),
        TIMESTAMP4 number(19,0),
        TIMESTAMP5 number(19,0),
        TIMESTAMP6 number(19,0),
        TIMESTAMP7 number(19,0),
        TIMESTAMP8 number(19,0),
        TIMESTAMP9 number(19,0),
        TIMESTAMP10 number(19,0),
        SHORTSTRING1 varchar2(16 char),
        SHORTSTRING2 varchar2(16 char),
        SHORTSTRING3 varchar2(16 char),
        SHORTSTRING4 varchar2(16 char),
        SHORTSTRING5 varchar2(16 char),
        SHORTSTRING6 varchar2(16 char),
        SHORTSTRING7 varchar2(16 char),
        SHORTSTRING8 varchar2(16 char),
        SHORTSTRING9 varchar2(16 char),
        SHORTSTRING10 varchar2(16 char),
        MIDDLESTRING1 varchar2(64 char),
        MIDDLESTRING2 varchar2(64 char),
        MIDDLESTRING3 varchar2(64 char),
        MIDDLESTRING4 varchar2(64 char),
        MIDDLESTRING5 varchar2(64 char),
        MIDDLESTRING6 varchar2(64 char),
        MIDDLESTRING7 varchar2(64 char),
        MIDDLESTRING8 varchar2(64 char),
        MIDDLESTRING9 varchar2(64 char),
        MIDDLESTRING10 varchar2(64 char),
        LONGSTRING1 varchar2(128 char),
        LONGSTRING2 varchar2(128 char),
        LONGSTRING3 varchar2(128 char),
        LONGSTRING4 varchar2(128 char),
        LONGSTRING5 varchar2(128 char),
        LONGSTRING6 varchar2(128 char),
        LONGSTRING7 varchar2(128 char),
        LONGSTRING8 varchar2(128 char),
        LONGSTRING9 varchar2(128 char),
        LONGSTRING10 varchar2(128 char),
        VERYLONGSTRING1 varchar2(256 char),
        VERYLONGSTRING2 varchar2(256 char),
        VERYLONGSTRING3 varchar2(256 char),
        VERYLONGSTRING4 varchar2(256 char),
        VERYLONGSTRING5 varchar2(256 char),
        VERYLONGSTRING6 varchar2(256 char),
        VERYLONGSTRING7 varchar2(256 char),
        VERYLONGSTRING8 varchar2(256 char),
        VERYLONGSTRING9 varchar2(256 char),
        VERYLONGSTRING10 varchar2(256 char),
        VERSION number(10,0) not null,
        primary key (ID)
    );

    create table TB_SDM_ASSETDYNAMICDETAILS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        REFID number(19,0),
        BOOLEAN1 number(1,0),
        BOOLEAN2 number(1,0),
        BOOLEAN3 number(1,0),
        BOOLEAN4 number(1,0),
        BOOLEAN5 number(1,0),
        BOOLEAN6 number(1,0),
        BOOLEAN7 number(1,0),
        BOOLEAN8 number(1,0),
        BOOLEAN9 number(1,0),
        BOOLEAN10 number(1,0),
        LONG1 number(19,0),
        LONG2 number(19,0),
        LONG3 number(19,0),
        LONG4 number(19,0),
        LONG5 number(19,0),
        LONG6 number(19,0),
        LONG7 number(19,0),
        LONG8 number(19,0),
        LONG9 number(19,0),
        LONG10 number(19,0),
        DOUBLE1 double precision,
        DOUBLE2 double precision,
        DOUBLE3 double precision,
        DOUBLE4 double precision,
        DOUBLE5 double precision,
        DOUBLE6 double precision,
        DOUBLE7 double precision,
        DOUBLE8 double precision,
        DOUBLE9 double precision,
        DOUBLE10 double precision,
        TIMESTAMP1 number(19,0),
        TIMESTAMP2 number(19,0),
        TIMESTAMP3 number(19,0),
        TIMESTAMP4 number(19,0),
        TIMESTAMP5 number(19,0),
        TIMESTAMP6 number(19,0),
        TIMESTAMP7 number(19,0),
        TIMESTAMP8 number(19,0),
        TIMESTAMP9 number(19,0),
        TIMESTAMP10 number(19,0),
        SHORTSTRING1 varchar2(16 char),
        SHORTSTRING2 varchar2(16 char),
        SHORTSTRING3 varchar2(16 char),
        SHORTSTRING4 varchar2(16 char),
        SHORTSTRING5 varchar2(16 char),
        SHORTSTRING6 varchar2(16 char),
        SHORTSTRING7 varchar2(16 char),
        SHORTSTRING8 varchar2(16 char),
        SHORTSTRING9 varchar2(16 char),
        SHORTSTRING10 varchar2(16 char),
        MIDDLESTRING1 varchar2(64 char),
        MIDDLESTRING2 varchar2(64 char),
        MIDDLESTRING3 varchar2(64 char),
        MIDDLESTRING4 varchar2(64 char),
        MIDDLESTRING5 varchar2(64 char),
        MIDDLESTRING6 varchar2(64 char),
        MIDDLESTRING7 varchar2(64 char),
        MIDDLESTRING8 varchar2(64 char),
        MIDDLESTRING9 varchar2(64 char),
        MIDDLESTRING10 varchar2(64 char),
        LONGSTRING1 varchar2(128 char),
        LONGSTRING2 varchar2(128 char),
        LONGSTRING3 varchar2(128 char),
        LONGSTRING4 varchar2(128 char),
        LONGSTRING5 varchar2(128 char),
        LONGSTRING6 varchar2(128 char),
        LONGSTRING7 varchar2(128 char),
        LONGSTRING8 varchar2(128 char),
        LONGSTRING9 varchar2(128 char),
        LONGSTRING10 varchar2(128 char),
        VERYLONGSTRING1 varchar2(256 char),
        VERYLONGSTRING2 varchar2(256 char),
        VERYLONGSTRING3 varchar2(256 char),
        VERYLONGSTRING4 varchar2(256 char),
        VERYLONGSTRING5 varchar2(256 char),
        VERYLONGSTRING6 varchar2(256 char),
        VERYLONGSTRING7 varchar2(256 char),
        VERYLONGSTRING8 varchar2(256 char),
        VERYLONGSTRING9 varchar2(256 char),
        VERYLONGSTRING10 varchar2(256 char),
        VERSION number(10,0) not null,
        primary key (ID)
    );

    create table TB_SDM_ASSETMESSAGEFIELDS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        PATH varchar2(128 char) not null,
        FIELDTYPE number(10,0) not null,
        valueClob clob,
        VALUELONG varchar2(128 char),
        VALUESHORT varchar2(16 char),
        VALUEVERYLONG varchar2(256 char),
        FKASSETMESSAGE number(19,0) not null,
        primary key (ID)
    );

    create table TB_SDM_ASSETMESSAGES (
        MESSAGECLASS varchar2(32 char) not null,
        ID number(19,0) not null,
        ASSETTYPE varchar2(64 char),
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        ISEXTENSION number(1,0),
        ISINPORTFOLIO number(1,0),
        ISMAINMARKET number(1,0),
        MESSAGEID varchar2(128 char),
        NORMOPERATIONCODE varchar2(6 char),
        FKASSET number(19,0),
        OPERATIONCODE varchar2(6 char),
        ISENDED number(1,0) not null,
        ISTRANSMESSERROR number(1,0) not null,
        TRANSMESSKEY varchar2(128 char),
        TRANSMESSPARM varchar2(256 char),
        ORIGINID varchar2(16 char),
        ORIGINNAME varchar2(512 char),
        ORIGINPOSITION number(10,0),
        ORIGINTYPE varchar2(64 char),
        ORIGINALMESSAGE clob,
        RECEIVER varchar2(64 char),
        SECURITY varchar2(64 char),
        SECURITYNAME varchar2(128 char),
        SECURITYTYPE varchar2(64 char),
        SENDER varchar2(64 char),
        SENDERTIMESTAMP timestamp,
        VERSION number(10,0) not null,
        FKASSETTYPE varchar2(4 char),
        FKSTATE varchar2(16 char) not null,
        FKSTATEFLOW varchar2(16 char) not null,
        FKPROVIDER number(19,0),
        primary key (ID)
    );

    create table TB_SDM_ASSETS (
        ASSETCLASS varchar2(32 char) not null,
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        ISENDED number(1,0) not null,
        ISTRANSMESSERROR number(1,0) not null,
        TRANSMESSKEY varchar2(128 char),
        TRANSMESSPARM varchar2(256 char),
        VERSION number(10,0) not null,
        FKASSETDETAIL number(19,0),
        FKASSETTYPE varchar2(4 char) not null,
        FKSTATE varchar2(16 char) not null,
        FKSTATEFLOW varchar2(16 char) not null,
        FKASSETPROVIDER number(19,0),
        primary key (ID)
    );

    create table TB_SDM_ASSETSDYNAMIC (
        DTYPE varchar2(31 char) not null,
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        REFID number(19,0),
        ISENDED number(1,0) not null,
        ISTRANSMESSERROR number(1,0) not null,
        TRANSMESSKEY varchar2(128 char),
        TRANSMESSPARM varchar2(256 char),
        VERSION number(10,0) not null,
        ENTITYOPERATIONALDATE date,
        FKASSETTYPE varchar2(4 char) not null,
        FKSTATE varchar2(16 char) not null,
        FKSTATEFLOW varchar2(16 char) not null,
        FKASSETDETAIL number(19,0),
        FKASSETGROUP number(19,0),
        FKASSETPROVIDER number(19,0),
        FKMASTERASSET number(19,0),
        primary key (ID)
    );

    create table TB_SDM_ASSETSSTATIC (
        DTYPE varchar2(31 char) not null,
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        REFID number(19,0),
        ISENDED number(1,0) not null,
        ISTRANSMESSERROR number(1,0) not null,
        TRANSMESSKEY varchar2(128 char),
        TRANSMESSPARM varchar2(256 char),
        VERSION number(10,0) not null,
        ENTITYOPERATIONALDATE date,
        FKASSETTYPE varchar2(4 char) not null,
        FKSTATE varchar2(16 char) not null,
        FKSTATEFLOW varchar2(16 char) not null,
        FKASSETDETAIL number(19,0),
        FKASSETGROUP number(19,0),
        FKASSETPROVIDER number(19,0),
        FKMASTERASSET number(19,0),
        primary key (ID)
    );

    create table TB_SDM_ASSETSTATICDETAILS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        REFID number(19,0),
        BOOLEAN1 number(1,0),
        BOOLEAN2 number(1,0),
        BOOLEAN3 number(1,0),
        BOOLEAN4 number(1,0),
        BOOLEAN5 number(1,0),
        BOOLEAN6 number(1,0),
        BOOLEAN7 number(1,0),
        BOOLEAN8 number(1,0),
        BOOLEAN9 number(1,0),
        BOOLEAN10 number(1,0),
        LONG1 number(19,0),
        LONG2 number(19,0),
        LONG3 number(19,0),
        LONG4 number(19,0),
        LONG5 number(19,0),
        LONG6 number(19,0),
        LONG7 number(19,0),
        LONG8 number(19,0),
        LONG9 number(19,0),
        LONG10 number(19,0),
        DOUBLE1 double precision,
        DOUBLE2 double precision,
        DOUBLE3 double precision,
        DOUBLE4 double precision,
        DOUBLE5 double precision,
        DOUBLE6 double precision,
        DOUBLE7 double precision,
        DOUBLE8 double precision,
        DOUBLE9 double precision,
        DOUBLE10 double precision,
        TIMESTAMP1 number(19,0),
        TIMESTAMP2 number(19,0),
        TIMESTAMP3 number(19,0),
        TIMESTAMP4 number(19,0),
        TIMESTAMP5 number(19,0),
        TIMESTAMP6 number(19,0),
        TIMESTAMP7 number(19,0),
        TIMESTAMP8 number(19,0),
        TIMESTAMP9 number(19,0),
        TIMESTAMP10 number(19,0),
        SHORTSTRING1 varchar2(16 char),
        SHORTSTRING2 varchar2(16 char),
        SHORTSTRING3 varchar2(16 char),
        SHORTSTRING4 varchar2(16 char),
        SHORTSTRING5 varchar2(16 char),
        SHORTSTRING6 varchar2(16 char),
        SHORTSTRING7 varchar2(16 char),
        SHORTSTRING8 varchar2(16 char),
        SHORTSTRING9 varchar2(16 char),
        SHORTSTRING10 varchar2(16 char),
        MIDDLESTRING1 varchar2(64 char),
        MIDDLESTRING2 varchar2(64 char),
        MIDDLESTRING3 varchar2(64 char),
        MIDDLESTRING4 varchar2(64 char),
        MIDDLESTRING5 varchar2(64 char),
        MIDDLESTRING6 varchar2(64 char),
        MIDDLESTRING7 varchar2(64 char),
        MIDDLESTRING8 varchar2(64 char),
        MIDDLESTRING9 varchar2(64 char),
        MIDDLESTRING10 varchar2(64 char),
        LONGSTRING1 varchar2(128 char),
        LONGSTRING2 varchar2(128 char),
        LONGSTRING3 varchar2(128 char),
        LONGSTRING4 varchar2(128 char),
        LONGSTRING5 varchar2(128 char),
        LONGSTRING6 varchar2(128 char),
        LONGSTRING7 varchar2(128 char),
        LONGSTRING8 varchar2(128 char),
        LONGSTRING9 varchar2(128 char),
        LONGSTRING10 varchar2(128 char),
        VERYLONGSTRING1 varchar2(256 char),
        VERYLONGSTRING2 varchar2(256 char),
        VERYLONGSTRING3 varchar2(256 char),
        VERYLONGSTRING4 varchar2(256 char),
        VERYLONGSTRING5 varchar2(256 char),
        VERYLONGSTRING6 varchar2(256 char),
        VERYLONGSTRING7 varchar2(256 char),
        VERYLONGSTRING8 varchar2(256 char),
        VERYLONGSTRING9 varchar2(256 char),
        VERYLONGSTRING10 varchar2(256 char),
        VERSION number(10,0) not null,
        primary key (ID)
    );

    create table TB_SDM_ASSETTYPES (
        ID varchar2(4 char) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        NAME varchar2(64 char),
        primary key (ID)
    );

    create table TB_SDM_ASSETTYPE_DETAILS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        ISBASIC number(1,0) not null,
        DESCRIPTION varchar2(256 char),
        DISPLAYCOLUMN number(10,0),
        DISPLAYGROUP varchar2(64 char) not null,
        DISPLAYGROUPORDER number(10,0) not null,
        DISPLAYINGROUPORDER number(10,0),
        ISDISPLAYINTABLE number(1,0) not null,
        DISPLAYINTABLEORDER number(10,0) not null,
        DISPLAYLEFT number(10,0),
        DISPLAYROW number(10,0),
        DISPLAYTOP number(10,0),
        ISEDITABLE number(1,0) not null,
        ISEDITABLENORMALIZATION number(1,0) not null,
        ISEXTENSION number(1,0) not null,
        FIELDPATH varchar2(32 char) not null,
        FIELDTYPE varchar2(16 char) not null,
        ISFILTER number(1,0) not null,
        FORMAT varchar2(32 char),
        ISHIDDEN number(1,0) not null,
        MAXLENGTH number(10,0) not null,
        NAME varchar2(64 char) not null,
        NATURE varchar2(1 char),
        ISSTORE number(1,0) not null,
        VERSION number(10,0) not null,
        FKASSETTYPE varchar2(4 char) not null,
        FKENTERPRISE number(19,0),
        primary key (ID)
    );

    create table TB_SDM_ASSETTYPE_DETAILS_BLK (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        BEGINBLOCKEDDATE timestamp not null,
        ENDBLOCKEDDATE timestamp not null,
        VERSION number(10,0) not null,
        FKASSETTYPEDETAIL number(19,0) not null,
        FKPROVIDER number(19,0) not null,
        primary key (ID)
    );

    create table TB_SDM_ENTERPRISE (
        ID number(19,0) not null,
        ADDRESS varchar2(255 char),
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        BIC varchar2(20 char),
        DESCRIPTION varchar2(255 char),
        NAME varchar2(50 char),
        TELEPHONE varchar2(12 char),
        VERSION number(10,0) not null,
        FKENTPARENT number(19,0),
        primary key (ID)
    );

    create table TB_SDM_ERROR_VALUES (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        ERROR varchar2(50 char),
        VERSION number(10,0) not null,
        FKVALUE number(19,0),
        primary key (ID)
    );

    create table TB_SDM_FIELDS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        DESCRIPTION varchar2(50 char),
        FIELDTYPE varchar2(50 char),
        LONG_DESCRIPTION varchar2(200 char),
        MASK varchar2(50 char),
        NAME varchar2(50 char),
        PATH varchar2(128 char) not null,
        VERSION number(10,0) not null,
        FKJOBTYPE number(19,0),
        primary key (ID)
    );

    create table TB_SDM_JOBS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        FILE_PATH varchar2(100 char),
        NFAILED number(10,0),
        NRECORDS number(10,0),
        NSUCCESS number(10,0),
        VERSION number(10,0) not null,
        FKJOBTYPE number(19,0),
        primary key (ID)
    );

    create table TB_SDM_JOBTYPE (
        ID number(19,0) not null,
        ALLORNOTHING number(1,0),
        IS_ALLOW_MULTITHREADING number(1,0),
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        CLASSEXE varchar2(50 char),
        COMMIT_DIRECTORY varchar2(200 char),
        COMMIT_SUFFIX varchar2(20 char),
        CRON_EXPRESSION varchar2(30 char),
        DESCRIPTION varchar2(255 char),
        INPUT_DIRECTORY varchar2(200 char),
        NAME varchar2(50 char),
        PATTERN varchar2(30 char),
        ROLLBACK_DIRECTORY varchar2(200 char),
        ROLLBACK_SUFFIX varchar2(20 char),
        TEMPORAL_SUFFIX varchar2(20 char),
        TYPE varchar2(32 char),
        VERSION number(10,0) not null,
        primary key (ID)
    );

    create table TB_SDM_JOBTYPE_FIELDS (
        ID number(19,0) not null,
        FIELDNAME varchar2(32 char),
        VALUE varchar2(64 char),
        FKJOBTYPE number(19,0),
        primary key (ID)
    );

    create table TB_SDM_MESSAGEFIELDCONFIGS (
        PATH varchar2(128 char) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        DESCRIPTION varchar2(128 char),
        DISPLAYCOLUMN number(10,0) not null,
        DISPLAYGROUP varchar2(64 char),
        DISPLAYROW number(10,0) not null,
        FIELDLENGTH number(10,0),
        FIELDNAME varchar2(64 char) not null,
        HIDDEN number(1,0) not null,
        VERSION number(10,0) not null,
        FKASSETTYPE varchar2(4 char) not null,
        FKPROVIDER number(19,0) not null,
        primary key (PATH, FKASSETTYPE, FKPROVIDER)
    );

    create table TB_SDM_MESSAGETYPES (
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        NAME varchar2(64 char) not null,
        FKPROVIDER number(19,0) not null,
        FKASSETTYPE varchar2(4 char) not null,
        primary key (FKASSETTYPE, FKPROVIDER)
    );

    create table TB_SDM_NORMALIZATIONS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        INCOMING_DATA_CLASS varchar2(128 char) not null,
        NAME varchar2(64 char) not null,
        OUTGOING_DATA_CLASS varchar2(128 char) not null,
        OUTGOING_FIELDS_CLASS varchar2(128 char) not null,
        VERSION number(10,0) not null,
        primary key (ID)
    );

    create table TB_SDM_NORMALIZATION_FILTERS (
        ID number(19,0) not null,
        CONDITION varchar2(512 char),
        FILTER_CLASS varchar2(128 char) not null,
        FILTER_NAME varchar2(64 char) not null,
        FKNORMALIZATION number(19,0),
        primary key (ID)
    );

    create table TB_SDM_NORMALIZATIONS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        INCOMING_DATA_CLASS varchar2(128 char) not null,
        NAME varchar2(64 char) not null,
        OUTGOING_DATA_CLASS varchar2(128 char) not null,
        OUTGOING_FIELDS_CLASS varchar2(128 char) not null,
        VERSION number(10,0) not null,
        primary key (ID)
    );

    create table TB_SDM_NORMALIZATION_FILTERS (
        ID number(19,0) not null,
        CONDITION varchar2(512 char) not null,
        FILTER_CLASS varchar2(128 char) not null,
        FILTER_NAME varchar2(64 char) not null,
        FKNORMALIZATION number(19,0),
        primary key (ID)
    );

    create table TB_SDM_PROVIDERS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        CODE varchar2(30 char) not null unique,
        ISDEFAULTPROVIDER number(1,0) not null,
        NAME varchar2(128 char) not null,
        NATURE varchar2(1 char) not null,
        VERSION number(10,0) not null,
        primary key (ID)
    );

    create table TB_SDM_ROWS (
        DISC_TYPE varchar2(31 char) not null,
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        ISENDED number(1,0) not null,
        ISTRANSMESSERROR number(1,0) not null,
        TRANSMESSKEY varchar2(128 char),
        TRANSMESSPARM varchar2(256 char),
        RECORD number(10,0),
        VALUE varchar2(1000 char),
        VERSION number(10,0) not null,
        ISIN varchar2(15 char),
        MIC varchar2(4 char),
        NAME varchar2(128 char),
        TICKER varchar2(32 char),
        FKJOB number(19,0),
        FKSTATE varchar2(16 char) not null,
        FKSTATEFLOW varchar2(16 char) not null,
        primary key (ID)
    );

    create table TB_SDM_SECEVENTS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        REFID number(19,0),
        ISCOMPLETE number(1,0),
        ISENDED number(1,0) not null,
        ISTRANSMESSERROR number(1,0) not null,
        TRANSMESSKEY varchar2(128 char),
        TRANSMESSPARM varchar2(256 char),
        version number(10,0) not null,
        FKASSETTYPE varchar2(4 char),
        FKMARKET number(19,0),
        FKSTATE varchar2(16 char) not null,
        FKSTATEFLOW varchar2(16 char) not null,
        primary key (ID)
    );

    create table TB_SDM_SECEVENT_DETAILS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        REFID number(19,0),
        BOOLEAN1 number(1,0),
        BOOLEAN2 number(1,0),
        BOOLEAN3 number(1,0),
        BOOLEAN4 number(1,0),
        BOOLEAN5 number(1,0),
        BOOLEAN6 number(1,0),
        BOOLEAN7 number(1,0),
        BOOLEAN8 number(1,0),
        BOOLEAN9 number(1,0),
        BOOLEAN10 number(1,0),
        LONG1 number(19,0),
        LONG2 number(19,0),
        LONG3 number(19,0),
        LONG4 number(19,0),
        LONG5 number(19,0),
        LONG6 number(19,0),
        LONG7 number(19,0),
        LONG8 number(19,0),
        LONG9 number(19,0),
        LONG10 number(19,0),
        DOUBLE1 double precision,
        DOUBLE2 double precision,
        DOUBLE3 double precision,
        DOUBLE4 double precision,
        DOUBLE5 double precision,
        DOUBLE6 double precision,
        DOUBLE7 double precision,
        DOUBLE8 double precision,
        DOUBLE9 double precision,
        DOUBLE10 double precision,
        TIMESTAMP1 number(19,0),
        TIMESTAMP2 number(19,0),
        TIMESTAMP3 number(19,0),
        TIMESTAMP4 number(19,0),
        TIMESTAMP5 number(19,0),
        TIMESTAMP6 number(19,0),
        TIMESTAMP7 number(19,0),
        TIMESTAMP8 number(19,0),
        TIMESTAMP9 number(19,0),
        TIMESTAMP10 number(19,0),
        SHORTSTRING1 varchar2(16 char),
        SHORTSTRING2 varchar2(16 char),
        SHORTSTRING3 varchar2(16 char),
        SHORTSTRING4 varchar2(16 char),
        SHORTSTRING5 varchar2(16 char),
        SHORTSTRING6 varchar2(16 char),
        SHORTSTRING7 varchar2(16 char),
        SHORTSTRING8 varchar2(16 char),
        SHORTSTRING9 varchar2(16 char),
        SHORTSTRING10 varchar2(16 char),
        MIDDLESTRING1 varchar2(64 char),
        MIDDLESTRING2 varchar2(64 char),
        MIDDLESTRING3 varchar2(64 char),
        MIDDLESTRING4 varchar2(64 char),
        MIDDLESTRING5 varchar2(64 char),
        MIDDLESTRING6 varchar2(64 char),
        MIDDLESTRING7 varchar2(64 char),
        MIDDLESTRING8 varchar2(64 char),
        MIDDLESTRING9 varchar2(64 char),
        MIDDLESTRING10 varchar2(64 char),
        LONGSTRING1 varchar2(128 char),
        LONGSTRING2 varchar2(128 char),
        LONGSTRING3 varchar2(128 char),
        LONGSTRING4 varchar2(128 char),
        LONGSTRING5 varchar2(128 char),
        LONGSTRING6 varchar2(128 char),
        LONGSTRING7 varchar2(128 char),
        LONGSTRING8 varchar2(128 char),
        LONGSTRING9 varchar2(128 char),
        LONGSTRING10 varchar2(128 char),
        VERYLONGSTRING1 varchar2(256 char),
        VERYLONGSTRING2 varchar2(256 char),
        VERYLONGSTRING3 varchar2(256 char),
        VERYLONGSTRING4 varchar2(256 char),
        VERYLONGSTRING5 varchar2(256 char),
        VERYLONGSTRING6 varchar2(256 char),
        VERYLONGSTRING7 varchar2(256 char),
        VERYLONGSTRING8 varchar2(256 char),
        VERYLONGSTRING9 varchar2(256 char),
        VERYLONGSTRING10 varchar2(256 char),
        VERSION number(10,0) not null,
        primary key (ID)
    );

    create table TB_SDM_SECEVENTS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        REFID number(19,0),
        ISCOMPLETE number(1,0),
        ISENDED number(1,0) not null,
        ISTRANSMESSERROR number(1,0) not null,
        TRANSMESSKEY varchar2(128 char),
        TRANSMESSPARM varchar2(256 char),
        version number(10,0) not null,
        FKASSETTYPE varchar2(4 char),
        FKMARKET number(19,0),
        FKSTATE varchar2(16 char) not null,
        FKSTATEFLOW varchar2(16 char) not null,
        primary key (ID)
    );

    create table TB_SDM_SECEVENT_DETAILS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        REFID number(19,0),
        BOOLEAN1 number(1,0),
        BOOLEAN2 number(1,0),
        BOOLEAN3 number(1,0),
        BOOLEAN4 number(1,0),
        BOOLEAN5 number(1,0),
        BOOLEAN6 number(1,0),
        BOOLEAN7 number(1,0),
        BOOLEAN8 number(1,0),
        BOOLEAN9 number(1,0),
        BOOLEAN10 number(1,0),
        LONG1 number(19,0),
        LONG2 number(19,0),
        LONG3 number(19,0),
        LONG4 number(19,0),
        LONG5 number(19,0),
        LONG6 number(19,0),
        LONG7 number(19,0),
        LONG8 number(19,0),
        LONG9 number(19,0),
        LONG10 number(19,0),
        DOUBLE1 double precision,
        DOUBLE2 double precision,
        DOUBLE3 double precision,
        DOUBLE4 double precision,
        DOUBLE5 double precision,
        DOUBLE6 double precision,
        DOUBLE7 double precision,
        DOUBLE8 double precision,
        DOUBLE9 double precision,
        DOUBLE10 double precision,
        TIMESTAMP1 number(19,0),
        TIMESTAMP2 number(19,0),
        TIMESTAMP3 number(19,0),
        TIMESTAMP4 number(19,0),
        TIMESTAMP5 number(19,0),
        TIMESTAMP6 number(19,0),
        TIMESTAMP7 number(19,0),
        TIMESTAMP8 number(19,0),
        TIMESTAMP9 number(19,0),
        TIMESTAMP10 number(19,0),
        SHORTSTRING1 varchar2(16 char),
        SHORTSTRING2 varchar2(16 char),
        SHORTSTRING3 varchar2(16 char),
        SHORTSTRING4 varchar2(16 char),
        SHORTSTRING5 varchar2(16 char),
        SHORTSTRING6 varchar2(16 char),
        SHORTSTRING7 varchar2(16 char),
        SHORTSTRING8 varchar2(16 char),
        SHORTSTRING9 varchar2(16 char),
        SHORTSTRING10 varchar2(16 char),
        MIDDLESTRING1 varchar2(64 char),
        MIDDLESTRING2 varchar2(64 char),
        MIDDLESTRING3 varchar2(64 char),
        MIDDLESTRING4 varchar2(64 char),
        MIDDLESTRING5 varchar2(64 char),
        MIDDLESTRING6 varchar2(64 char),
        MIDDLESTRING7 varchar2(64 char),
        MIDDLESTRING8 varchar2(64 char),
        MIDDLESTRING9 varchar2(64 char),
        MIDDLESTRING10 varchar2(64 char),
        LONGSTRING1 varchar2(128 char),
        LONGSTRING2 varchar2(128 char),
        LONGSTRING3 varchar2(128 char),
        LONGSTRING4 varchar2(128 char),
        LONGSTRING5 varchar2(128 char),
        LONGSTRING6 varchar2(128 char),
        LONGSTRING7 varchar2(128 char),
        LONGSTRING8 varchar2(128 char),
        LONGSTRING9 varchar2(128 char),
        LONGSTRING10 varchar2(128 char),
        VERYLONGSTRING1 varchar2(256 char),
        VERYLONGSTRING2 varchar2(256 char),
        VERYLONGSTRING3 varchar2(256 char),
        VERYLONGSTRING4 varchar2(256 char),
        VERYLONGSTRING5 varchar2(256 char),
        VERYLONGSTRING6 varchar2(256 char),
        VERYLONGSTRING7 varchar2(256 char),
        VERYLONGSTRING8 varchar2(256 char),
        VERYLONGSTRING9 varchar2(256 char),
        VERYLONGSTRING10 varchar2(256 char),
        VERSION number(10,0) not null,
        primary key (ID)
    );

    create table TB_SDM_STATUS (
        ID number(19,0) not null,
        CODE varchar2(50 char),
        DESCRIPTION varchar2(255 char),
        NAME varchar2(50 char),
        primary key (ID)
    );

    create table TB_SDM_STCONFIGFIELDS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        ISREQUIRED number(1,0) not null,
        VERSION number(10,0) not null,
        FKASSETTYPEDETAIL number(19,0) not null,
        FKSTATICCONFIG number(19,0),
        primary key (ID),
        unique (FKSTATICCONFIG, FKASSETTYPEDETAIL)
    );

    create table TB_SDM_STCONFIGMSGFLDS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        NORMSCRIPT varchar2(2048 char),
        VERSION number(10,0) not null,
        FKASSETTYPE varchar2(4 char) not null,
        FKENTERPRISE number(19,0) not null,
        FKPROVIDER number(19,0),
        FKSTFIELDCONFIG number(19,0) not null,
        primary key (ID),
        unique (FKSTFIELDCONFIG, FKPROVIDER, FKASSETTYPE)
    );

    create table TB_SDM_STCONFIGS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        VERSION number(10,0) not null,
        FKASSETTYPE varchar2(4 char) not null,
        FKPROVIDER5 number(19,0),
        FKPROVIDER4 number(19,0),
        FKPROVIDER1 number(19,0) not null,
        FKPROVIDER2 number(19,0),
        FKPROVIDER3 number(19,0),
        primary key (ID),
        unique (FKASSETTYPE)
    );

    create table TB_SDM_STMRCONFIGS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        CONDITION varchar2(32 char),
        CONDITIONPARAMS varchar2(128 char),
        ONFALSERESULT varchar2(32 char),
        ONTRUERESULT varchar2(32 char),
        RULEORDER number(10,0),
        TARGET varchar2(64 char),
        FKASSETTYPEDETAIL number(19,0) not null,
        FKSTCONFIG number(19,0) not null,
        primary key (ID),
        unique (FKSTCONFIG, RULEORDER)
    );

    create table TB_SDM_VALUES (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        VALUE varchar2(100 char),
        VERSION number(10,0) not null,
        FKJOBFIELD number(19,0),
        FKROW number(19,0) not null,
        primary key (ID)
    );

    create table TB_SECURITY_CONSTRAINT (
        COLLECTION_NAME varchar2(20 char) not null,
        GROUP_ID varchar2(30 char) not null,
        primary key (COLLECTION_NAME, GROUP_ID)
    );

    create table TB_SP_CUSTODIAN_ACCOUNTS (
        ID number(19,0) not null,
        ACCOUNTNUMBER varchar2(64 char) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        NAME varchar2(64 char) not null,
        OWNACCOUNT number(1,0) not null,
        VERSION number(10,0) not null,
        FKPROVIDER varchar2(16 char),
        primary key (ID)
    );

    create table TB_SP_CUSTOMERS (
        MANAGERTYPE varchar2(16 char) not null,
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        CUSTOMERID varchar2(64 char),
        NAME varchar2(64 char) not null,
        VERSION number(10,0) not null,
        FKCUSTMANAGERGROUP number(19,0),
        FKCUSTMANAGER number(19,0),
        primary key (ID)
    );

    create table TB_SP_MANAGERGROUPS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        CUSTOMERID varchar2(64 char),
        NAME varchar2(64 char) not null,
        VERSION number(10,0) not null,
        primary key (ID)
    );

    create table TB_SP_MANAGERS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        CUSTOMERID varchar2(64 char),
        EMAIL varchar2(64 char),
        NAME varchar2(64 char) not null,
        VERSION number(10,0) not null,
        FKMANAGERGROUP number(19,0),
        primary key (ID)
    );

    create table TB_SP_MARKETS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        CITY varchar2(32 char),
        COUNTRY varchar2(2 char),
        CUSTOMERID varchar2(64 char),
        MIC varchar2(4 char) not null,
        NAME varchar2(128 char),
        TICKER varchar2(32 char),
        VERSION number(10,0) not null,
        primary key (ID)
    );

    create table TB_SP_PLANNING_PROCESS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        REFID number(19,0),
        CRON varchar2(30 char) not null,
        EXECUTIONDATE timestamp,
        MANUAL number(1,0),
        NAME varchar2(30 char) not null,
        TEMPLATE blob,
        VERSION number(10,0) not null,
        FKPROCESS number(19,0),
        primary key (ID)
    );

    create table TB_SP_PROCESS (
        PROCESSTYPE varchar2(16 char) not null,
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        REFID number(19,0),
        DESCRIPTION varchar2(16 char) not null,
        VERSION number(10,0) not null,
        FILEEXTENSION varchar2(6 char) not null,
        FILENAME varchar2(64 char) not null,
        OVERWRITE number(1,0) not null,
        PATH varchar2(64 char) not null,
        FTP_BINARY number(1,0),
        FTP_FILE varchar2(128 char),
        FTP_PASSIVE_MODE number(1,0),
        FTP_PASSWORD varchar2(128 char),
        FTP_PATH varchar2(256 char),
        FTP_PORT varchar2(6 char),
        FTP_PROXY varchar2(128 char),
        PROXY_PASSWORD varchar2(128 char),
        PROXY_USER varchar2(128 char),
        FTP_SERVER varchar2(128 char),
        FTP_USER varchar2(128 char),
        SHELL varchar2(256 char),
        primary key (ID)
    );

    create table TB_SP_SECURITIES (
        SECURITYCLASS varchar2(16 char) not null,
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        COUNTRY varchar2(2 char),
        CUSIP varchar2(9 char),
        INCUSTPORTFOLIO number(1,0) not null,
        IND_GROUP varchar2(32 char),
        IND_SECTOR varchar2(32 char),
        IND_SUBGROUP varchar2(32 char),
        PROVIDER_ID1 varchar2(16 char),
        PROVIDER_ID2 varchar2(16 char),
        PROVIDER_ID3 varchar2(16 char),
        ISIN varchar2(12 char),
        NAME varchar2(64 char) not null,
        REL_INDEX varchar2(8 char),
        SEC_TYPE varchar2(32 char),
        SEDOL varchar2(7 char),
        VERSION number(10,0) not null,
        CURRENCY varchar2(3 char),
        CUSTOMERID varchar2(64 char),
        EXPIRATIONDATE date,
        TICKER varchar2(32 char),
        FKPLANIFICATION number(19,0),
        FKFINANCIALASSET varchar2(4 char),
        FKDETAIL number(19,0),
        FKMARKET number(19,0),
        FKSECURITYETF number(19,0),
        FKSECURITYEQUITY number(19,0),
        FKSECURITYFIXEDINCOME number(19,0),
        FKSECURITYFUND number(19,0),
        primary key (ID)
    );

    create table TB_SP_SECURITYDETAILS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        SHORTSTRING1 varchar2(16 char),
        SHORTSTRING2 varchar2(16 char),
        SHORTSTRING3 varchar2(16 char),
        SHORTSTRING4 varchar2(16 char),
        SHORTSTRING5 varchar2(16 char),
        SHORTSTRING6 varchar2(16 char),
        SHORTSTRING7 varchar2(16 char),
        SHORTSTRING8 varchar2(16 char),
        SHORTSTRING9 varchar2(16 char),
        SHORTSTRING10 varchar2(16 char),
        SHORTSTRING11 varchar2(16 char),
        SHORTSTRING12 varchar2(16 char),
        SHORTSTRING13 varchar2(16 char),
        SHORTSTRING14 varchar2(16 char),
        SHORTSTRING15 varchar2(16 char),
        SHORTSTRING16 varchar2(16 char),
        SHORTSTRING17 varchar2(16 char),
        SHORTSTRING18 varchar2(16 char),
        SHORTSTRING19 varchar2(16 char),
        SHORTSTRING20 varchar2(16 char),
        SHORTSTRING21 varchar2(16 char),
        SHORTSTRING22 varchar2(16 char),
        SHORTSTRING23 varchar2(16 char),
        SHORTSTRING24 varchar2(16 char),
        SHORTSTRING25 varchar2(16 char),
        SHORTSTRING26 varchar2(16 char),
        SHORTSTRING27 varchar2(16 char),
        SHORTSTRING28 varchar2(16 char),
        SHORTSTRING29 varchar2(16 char),
        SHORTSTRING30 varchar2(16 char),
        SHORTSTRING31 varchar2(16 char),
        SHORTSTRING32 varchar2(16 char),
        SHORTSTRING33 varchar2(16 char),
        SHORTSTRING34 varchar2(16 char),
        SHORTSTRING35 varchar2(16 char),
        SHORTSTRING36 varchar2(16 char),
        SHORTSTRING37 varchar2(16 char),
        SHORTSTRING38 varchar2(16 char),
        SHORTSTRING39 varchar2(16 char),
        SHORTSTRING40 varchar2(16 char),
        MIDDLESTRING1 varchar2(32 char),
        MIDDLESTRING2 varchar2(32 char),
        MIDDLESTRING3 varchar2(32 char),
        MIDDLESTRING4 varchar2(32 char),
        MIDDLESTRING5 varchar2(32 char),
        MIDDLESTRING6 varchar2(32 char),
        MIDDLESTRING7 varchar2(32 char),
        MIDDLESTRING8 varchar2(32 char),
        MIDDLESTRING9 varchar2(32 char),
        MIDDLESTRING10 varchar2(32 char),
        MIDDLESTRING11 varchar2(32 char),
        MIDDLESTRING12 varchar2(32 char),
        MIDDLESTRING13 varchar2(32 char),
        MIDDLESTRING14 varchar2(32 char),
        MIDDLESTRING15 varchar2(32 char),
        MIDDLESTRING16 varchar2(32 char),
        MIDDLESTRING17 varchar2(32 char),
        MIDDLESTRING18 varchar2(32 char),
        MIDDLESTRING19 varchar2(32 char),
        MIDDLESTRING20 varchar2(32 char),
        MIDDLESTRING21 varchar2(32 char),
        MIDDLESTRING22 varchar2(32 char),
        MIDDLESTRING23 varchar2(32 char),
        MIDDLESTRING24 varchar2(32 char),
        MIDDLESTRING25 varchar2(32 char),
        MIDDLESTRING26 varchar2(32 char),
        MIDDLESTRING27 varchar2(32 char),
        MIDDLESTRING28 varchar2(32 char),
        MIDDLESTRING29 varchar2(32 char),
        MIDDLESTRING30 varchar2(32 char),
        MIDDLESTRING31 varchar2(32 char),
        MIDDLESTRING32 varchar2(32 char),
        MIDDLESTRING33 varchar2(32 char),
        MIDDLESTRING34 varchar2(32 char),
        MIDDLESTRING35 varchar2(32 char),
        MIDDLESTRING36 varchar2(32 char),
        MIDDLESTRING37 varchar2(32 char),
        MIDDLESTRING38 varchar2(32 char),
        MIDDLESTRING39 varchar2(32 char),
        MIDDLESTRING40 varchar2(32 char),
        LONGSTRING1 varchar2(64 char),
        LONGSTRING2 varchar2(64 char),
        LONGSTRING3 varchar2(64 char),
        LONGSTRING4 varchar2(64 char),
        LONGSTRING5 varchar2(64 char),
        LONGSTRING6 varchar2(64 char),
        LONGSTRING7 varchar2(64 char),
        LONGSTRING8 varchar2(64 char),
        LONGSTRING9 varchar2(64 char),
        LONGSTRING10 varchar2(64 char),
        LONGSTRING11 varchar2(64 char),
        LONGSTRING12 varchar2(64 char),
        LONGSTRING13 varchar2(64 char),
        LONGSTRING14 varchar2(64 char),
        LONGSTRING15 varchar2(64 char),
        LONGSTRING16 varchar2(64 char),
        LONGSTRING17 varchar2(64 char),
        LONGSTRING18 varchar2(64 char),
        LONGSTRING19 varchar2(64 char),
        LONGSTRING20 varchar2(64 char),
        LONGSTRING21 varchar2(64 char),
        LONGSTRING22 varchar2(64 char),
        LONGSTRING23 varchar2(64 char),
        LONGSTRING24 varchar2(64 char),
        LONGSTRING25 varchar2(64 char),
        LONGSTRING26 varchar2(64 char),
        LONGSTRING27 varchar2(64 char),
        LONGSTRING28 varchar2(64 char),
        LONGSTRING29 varchar2(64 char),
        LONGSTRING30 varchar2(64 char),
        LONGSTRING31 varchar2(64 char),
        LONGSTRING32 varchar2(64 char),
        LONGSTRING33 varchar2(64 char),
        LONGSTRING34 varchar2(64 char),
        LONGSTRING35 varchar2(64 char),
        LONGSTRING36 varchar2(64 char),
        LONGSTRING37 varchar2(64 char),
        LONGSTRING38 varchar2(64 char),
        LONGSTRING39 varchar2(64 char),
        LONGSTRING40 varchar2(64 char),
        VERYLONGSTRING1 varchar2(128 char),
        VERYLONGSTRING2 varchar2(128 char),
        VERYLONGSTRING3 varchar2(128 char),
        VERYLONGSTRING4 varchar2(128 char),
        VERYLONGSTRING5 varchar2(128 char),
        VERYLONGSTRING6 varchar2(128 char),
        VERYLONGSTRING7 varchar2(128 char),
        VERYLONGSTRING8 varchar2(128 char),
        VERYLONGSTRING9 varchar2(128 char),
        VERYLONGSTRING10 varchar2(128 char),
        VERYLONGSTRING11 varchar2(128 char),
        VERYLONGSTRING12 varchar2(128 char),
        VERYLONGSTRING13 varchar2(128 char),
        VERYLONGSTRING14 varchar2(128 char),
        VERYLONGSTRING15 varchar2(128 char),
        VERYLONGSTRING16 varchar2(128 char),
        VERYLONGSTRING17 varchar2(128 char),
        VERYLONGSTRING18 varchar2(128 char),
        VERYLONGSTRING19 varchar2(128 char),
        VERYLONGSTRING20 varchar2(128 char),
        VERYLONGSTRING21 varchar2(128 char),
        VERYLONGSTRING22 varchar2(128 char),
        VERYLONGSTRING23 varchar2(128 char),
        VERYLONGSTRING24 varchar2(128 char),
        VERYLONGSTRING25 varchar2(128 char),
        VERYLONGSTRING26 varchar2(128 char),
        VERYLONGSTRING27 varchar2(128 char),
        VERYLONGSTRING28 varchar2(128 char),
        VERYLONGSTRING29 varchar2(128 char),
        VERYLONGSTRING30 varchar2(128 char),
        VERYLONGSTRING31 varchar2(128 char),
        VERYLONGSTRING32 varchar2(128 char),
        VERYLONGSTRING33 varchar2(128 char),
        VERYLONGSTRING34 varchar2(128 char),
        VERYLONGSTRING35 varchar2(128 char),
        VERYLONGSTRING36 varchar2(128 char),
        VERYLONGSTRING37 varchar2(128 char),
        VERYLONGSTRING38 varchar2(128 char),
        VERYLONGSTRING39 varchar2(128 char),
        VERYLONGSTRING40 varchar2(128 char),
        VERSION number(10,0) not null,
        primary key (ID)
    );

    create table TB_SP_SECURITYPORTFOLIO (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        REFID number(19,0),
        CUSTODIANAMOUNT number(19,0),
        CUSTOMERAMOUNT number(19,0),
        EMISSIONDATE date not null,
        VERSION number(10,0) not null,
        FKPROVIDERACCOUNT number(19,0),
        FKCUSTOMER number(19,0),
        FKSECURITY number(19,0) not null,
        primary key (ID)
    );

    create table TB_SP_SECURITY_EQUITY (
        ID number(19,0) not null,
        ADR_SH_PER_SH varchar2(255 char),
        ADR_SH_PER_ADR varchar2(255 char),
        CNTRY varchar2(255 char),
        COUNTRY varchar2(255 char),
        DVD_CRNCY varchar2(255 char),
        DVD_DECLARED_DT varchar2(255 char),
        DVD_EX_DT varchar2(255 char),
        DVD_FREQ varchar2(255 char),
        DVD_PAY_DT varchar2(255 char),
        DVD_RECORD_DT varchar2(255 char),
        DVD_LAST varchar2(255 char),
        DVD_TYP_LAST varchar2(255 char),
        EQY_SH_PO_DT varchar2(255 char),
        EQY_PO_DT varchar2(255 char),
        EQY_PRIM_EXCH varchar2(255 char),
        EQY_PRIM_SECURITY_TICKER varchar2(255 char),
        EQY_SH_OUT varchar2(255 char),
        EQY_SH_OUT_DT varchar2(255 char),
        EQY_SH_OUT_TOT varchar2(255 char),
        EQY_SPLIT_DT varchar2(255 char),
        EQY_SPLIT_RATIO varchar2(255 char),
        ID_BB_COMPANY varchar2(255 char),
        ID_BB_SECURITY varchar2(255 char),
        ID_MIC_LOCAL varchar2(255 char),
        ID_MIC varchar2(255 char),
        INDUSTRY_SUBGROUP varchar2(255 char),
        LAST_DPS varchar2(255 char),
        LONG_COMP_NAME varchar2(255 char),
        MARKET_SECTOR varchar2(255 char),
        MARKET_STATUS varchar2(255 char),
        MULTIPLE_SHARE varchar2(255 char),
        PAR_AMT varchar2(255 char),
        PAR_VAL_CRNCY varchar2(255 char),
        PX_QUOTE_LOT_SIZE varchar2(255 char),
        PX_TRADE_LOT_SIZE varchar2(255 char),
        REL_INDEX varchar2(255 char),
        SECURITY_TYPE2 varchar2(255 char),
        TICKER varchar2(255 char),
        TOTAL_VOTING varchar2(255 char),
        VOTING_RIGHTS varchar2(255 char),
        primary key (ID)
    );

    create table TB_SP_SECURITY_ETF (
        ID number(19,0) not null,
        CNTRY_ISSUE varchar2(255 char),
        CNTRY_OF_INCORPORATION varchar2(255 char),
        DVD_CRNCY varchar2(255 char),
        DVD_DECLARED_DT varchar2(255 char),
        DVD_EX_DT varchar2(255 char),
        DVD_FREQ varchar2(255 char),
        DVD_PAY_DT varchar2(255 char),
        DVD_RECORD_DT varchar2(255 char),
        DVD_SH_LAST varchar2(255 char),
        DVD_TYP_LAST varchar2(255 char),
        EQY_PRIM_EXCH varchar2(255 char),
        EQY_SPLIT_DT varchar2(255 char),
        EQY_SPLIT_RATIO varchar2(255 char),
        FUND_ASSET_CLASS_FOCUS varchar2(255 char),
        FUND_CUSTODIAN_LONG varchar2(255 char),
        FUND_GEO_FOCUS varchar2(255 char),
        FUND_INCEPT_DT varchar2(255 char),
        FUND_MANAGEMENT_CO varchar2(255 char),
        FUND_MGMT_STYLE varchar2(255 char),
        FUND_MGR_FEE varchar2(255 char),
        FUND_MIN_INVEST varchar2(255 char),
        FUND_OBJECTIVE_LONG varchar2(255 char),
        FUND_OPEN_PYMNT_SHR varchar2(255 char),
        FUND_OPEN_SHR_CLASS varchar2(255 char),
        FUND_STRATEGY varchar2(255 char),
        FUND_TYPE varchar2(255 char),
        ID_BB_COMPANY varchar2(255 char),
        ID_BB_UNIQUE varchar2(255 char),
        ID_MIC_PRIM_EXCH varchar2(255 char),
        INDUSTRY_SUBGROUP varchar2(255 char),
        long_comp_name varchar2(255 char),
        MARKET_SECTOR_DES varchar2(255 char),
        MARKET_STATUS varchar2(255 char),
        MIN_SUBSQUENT_INVEST varchar2(255 char),
        PX_TRADE_LOT_SIZE varchar2(255 char),
        REL_INDEX varchar2(255 char),
        SEC_DES varchar2(255 char),
        SECURITY_TYPE2 varchar2(255 char),
        TRANSFER_AGENT varchar2(255 char),
        primary key (ID)
    );

    create table TB_SP_SECURITY_FASSETS (
        ID varchar2(4 char) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        NAME varchar2(64 char),
        primary key (ID)
    );

    create table TB_SP_SECURITY_FASSETS_DETAILS (
        ID number(19,0) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        UPDDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        ISBASIC number(1,0) not null,
        DESCRIPTION varchar2(256 char),
        DISPLAYCOLUMN number(10,0),
        DISPLAYGROUP varchar2(64 char) not null,
        DISPLAYGROUPORDER number(10,0) not null,
        DISPLAYINGROUPORDER number(10,0),
        ISDISPLAYINTABLE number(1,0) not null,
        DISPLAYINTABLEORDER number(10,0) not null,
        DISPLAYLEFT number(10,0),
        DISPLAYROW number(10,0),
        DISPLAYTOP number(10,0),
        ISEXTENSION number(1,0) not null,
        FIELDPATH varchar2(32 char) not null,
        FIELDTYPE varchar2(16 char) not null,
        FORMAT varchar2(32 char),
        ISHIDDEN number(1,0) not null,
        MAXLENGTH number(10,0) not null,
        NAME varchar2(64 char) not null,
        ISSTORE number(1,0) not null,
        VERSION number(10,0) not null,
        FKSECURITYFA varchar2(4 char) not null,
        primary key (ID)
    );

    create table TB_SP_SECURITY_FIXED (
        ID number(19,0) not null,
        amt_issued varchar2(255 char),
        amt_outstanding varchar2(255 char),
        announce_dt varchar2(255 char),
        basic_spread varchar2(255 char),
        calc_typ_des varchar2(255 char),
        collat_typ varchar2(255 char),
        country varchar2(255 char),
        cpn varchar2(255 char),
        cpn_crncy varchar2(255 char),
        cpn_freq varchar2(255 char),
        cpn_typ varchar2(255 char),
        extendible varchar2(255 char),
        final_maturity varchar2(255 char),
        first_cpn_dt varchar2(255 char),
        id_bb_unique varchar2(255 char),
        is_perpetual varchar2(255 char),
        is_unit_traded varchar2(255 char),
        issue_dt varchar2(255 char),
        issue_px varchar2(255 char),
        issuer varchar2(255 char),
        issuer_industry varchar2(255 char),
        long_comp_name varchar2(255 char),
        market_issue varchar2(255 char),
        maturity varchar2(255 char),
        min_increment varchar2(255 char),
        min_piece varchar2(255 char),
        mty_typ varchar2(255 char),
        par_amt varchar2(255 char),
        post_euro_day_cnt_des varchar2(255 char),
        pre_euro_day_cnt_des varchar2(255 char),
        prev_cpn_dt varchar2(255 char),
        redemp_crncy varchar2(255 char),
        redemp_val varchar2(255 char),
        reset_idx varchar2(255 char),
        security_des varchar2(255 char),
        series varchar2(255 char),
        short_name varchar2(255 char),
        structured_note varchar2(255 char),
        trade_crncy varchar2(255 char),
        trade_status varchar2(255 char),
        primary key (ID)
    );

    create table TB_SP_SECURITY_FUND (
        ID number(19,0) not null,
        CNTRY_ISSUE varchar2(255 char),
        CNTRY_OF_INCORPORATION varchar2(255 char),
        DVD_CRNCY varchar2(255 char),
        DVD_DECLARED_DT varchar2(255 char),
        DVD_EX_DT varchar2(255 char),
        DVD_FREQ varchar2(255 char),
        DVD_PAY_DT varchar2(255 char),
        DVD_RECORD_DT varchar2(255 char),
        DVD_SH_LAST varchar2(255 char),
        DVD_TYP_LAST varchar2(255 char),
        EQY_PRIM_EXCH varchar2(255 char),
        EQY_SPLIT_DT varchar2(255 char),
        EQY_SPLIT_RATIO varchar2(255 char),
        FUND_ASSET_CLASS_FOCUS varchar2(255 char),
        FUND_CUSTODIAN_LONG varchar2(255 char),
        FUND_GEO_FOCUS varchar2(255 char),
        FUND_INCEPT_DT varchar2(255 char),
        FUND_MANAGEMENT_CO varchar2(255 char),
        FUND_MGMT_STYLE varchar2(255 char),
        FUND_MGR_FEE varchar2(255 char),
        FUND_MIN_INVEST varchar2(255 char),
        FUND_OBJECTIVE_LONG varchar2(255 char),
        FUND_OPEN_PYMNT_SHR varchar2(255 char),
        FUND_OPEN_SHR_CLASS varchar2(255 char),
        FUND_STRATEGY varchar2(255 char),
        FUND_TYPE varchar2(255 char),
        ID_BB_COMPANY varchar2(255 char),
        ID_BB_UNIQUE varchar2(255 char),
        ID_MIC_PRIM_EXCH varchar2(255 char),
        INDUSTRY_SUBGROUP varchar2(255 char),
        long_comp_name varchar2(255 char),
        MARKET_SECTOR_DES varchar2(255 char),
        MARKET_STATUS varchar2(255 char),
        MIN_SUBSQUENT_INVEST varchar2(255 char),
        PX_TRADE_LOT_SIZE varchar2(255 char),
        REL_INDEX varchar2(255 char),
        SEC_DES varchar2(255 char),
        SECURITY_TYPE2 varchar2(255 char),
        TRANSFER_AGENT varchar2(255 char),
        primary key (ID)
    );

    create table TB_SP_SECURITY_PLANNING (
        ID number(19,0) not null,
        FKPLANNING number(19,0),
        FKSECURITY number(19,0),
        primary key (ID)
    );

    create table TB_STATES (
        CODE varchar2(16 char) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        DESCRIPTION varchar2(128 char),
        ISEND number(1,0) not null,
        ISINITIAL number(1,0) not null,
        NAME varchar2(64 char) not null,
        FKFLOW varchar2(16 char) not null,
        primary key (CODE, FKFLOW)
    );

    create table TB_TRACKING (
        TRACKING_ID number(19,0) not null,
        USER_ID varchar2(20 char),
        SESSION_ID varchar2(255 char),
        SESSION_START timestamp,
        SESSION_END timestamp,
        UNIQUE_IP varchar2(15 char),
        PLATFORM varchar2(50 char),
        SCREEN_SIZE varchar2(15 char),
        BROWSER varchar2(50 char),
        USER_AGENT varchar2(150 char),
        BROWSER_NAME varchar2(30 char),
        BROWSER_VERSION varchar2(5 char),
        primary key (TRACKING_ID)
    );

    create table TB_TRANSITIONS (
        CODE varchar2(16 char) not null,
        CRTDATE timestamp not null,
        CRTUSER varchar2(128 char) not null,
        DESCRIPTION varchar2(128 char),
        NAME varchar2(64 char) not null,
        TRANSITIONEXECLASS varchar2(128 char),
        TRANSITIONMESSAGE varchar2(128 char),
        FKFLOW varchar2(16 char) not null,
        FKENDSTATE varchar2(16 char) not null,
        FKENDSTATEFLOW varchar2(16 char) not null,
        FKINITIALSTATE varchar2(16 char),
        FKINITIALSTATEFLOW varchar2(16 char),
        primary key (CODE, FKFLOW)
    );

    create table TB_USER_GROUP (
        USER_ID varchar2(30 char) not null,
        GROUP_ID varchar2(30 char)
    );

    create table TB_USER_PROFILES (
        USER_ID varchar2(30 char) not null,
        USER_TYPE varchar2(20 char) not null,
        PASSWORD varchar2(30 char),
        PASSWORD_EXPIRATION_DATE date,
        FIRSTNAME varchar2(50 char),
        LASTNAME varchar2(50 char),
        LANGUAGE varchar2(2 char),
        COUNTRY varchar2(2 char),
        EMAIL varchar2(50 char),
        GENDER varchar2(1 char),
        BIRTHDATE date,
        HOMEPAGE varchar2(255 char),
        EXPIRATION_DATE date,
        ADDRESS varchar2(255 char),
        CITY varchar2(50 char),
        PROV varchar2(20 char),
        ZIPCODE varchar2(20 char),
        STATE varchar2(20 char),
        PHONE varchar2(20 char),
        MOBILE varchar2(20 char),
        FAX varchar2(20 char),
        ID_USER_STATUS number(10,0),
        primary key (USER_ID)
    );

    create table TB_USER_TYPE (
        TYPE varchar2(20 char) not null,
        CLASSNAME varchar2(100 char),
        primary key (TYPE)
    );

    create index IDX_HSEVDETAIL_DELETED on HS_CA_EVENTDETAILS (ISDELETED);

    create index IDX_HSEVDETAIL_VERSION on HS_CA_EVENTDETAILS (VERSION);

    create index IDX_HSEVDETAIL_REFID on HS_CA_EVENTDETAILS (REFID);

    create index IDX_HSEVDETAIL_OPERDATE on HS_CA_EVENTDETAILS (OPERATIONALDATE);

    create index IDX_HSEXTDETAIL_FKEVENTDETAIL on HS_CA_EVENTEXTDETAILS (FKEVENTDETAIL);

    create index IDX_HSEVTEXTDETAIL_VERSION on HS_CA_EVENTEXTDETAILS (VERSION);

    create index IDX_HSEVTEXTDETAIL_EXTENSIONID on HS_CA_EVENTEXTDETAILS (EXTENSIONNAME, EXTENSIONNUMBER);

    create index IDX_HSEVTEXTDETAIL_REFID on HS_CA_EVENTEXTDETAILS (REFID);

    create index IDX_HSEVTEXTDETAIL_DELETED on HS_CA_EVENTEXTDETAILS (ISDELETED);

    alter table HS_CA_EVENTEXTDETAILS 
        add constraint FK_HSEXTENDED_DETAIL 
        foreign key (FKEVENTDETAIL) 
        references HS_CA_EVENTDETAILS;

    create index IDX_HSEVMESGFLD_FKEVENTMESSAGE on HS_CA_EVENTMESSAGEFIELDS (FKHSEVENTMESSAGE);

    alter table HS_CA_EVENTMESSAGEFIELDS 
        add constraint FK_HSEVENTFIELD_MESSAGE 
        foreign key (FKHSEVENTMESSAGE) 
        references HS_CA_EVENTMESSAGES;

    create index IDX_HSEVMESSAGE_PROVIDER on HS_CA_EVENTMESSAGES (FKPROVIDER);

    create index IDX_HSEVMESSAGE_EFFECTIVEDATE on HS_CA_EVENTMESSAGES (EFFECTIVEDATE);

    create index IDX_HSEVMESSAGE_DELETED on HS_CA_EVENTMESSAGES (ISDELETED);

    create index IDX_HSEVMESSAGE_FKEVENTTYPE on HS_CA_EVENTMESSAGES (FKEVENTTYPE);

    create index IDX_HSEVMESSAGE_MESSAGEID on HS_CA_EVENTMESSAGES (MESSAGEID);

    create index IDX_HSEVMESSAGE_FKFORMAT on HS_CA_EVENTMESSAGES (FKFORMAT);

    create index IDX_HSEVMESSAGE_FKEVENT on HS_CA_EVENTMESSAGES (FKEVENT);

    create index IDX_HSEVMESSAGE_ISINPORTFOLIO on HS_CA_EVENTMESSAGES (ISINPORTFOLIO);

    create index IDX_HSEVMESSAGE_SENDTIMESTAMP on HS_CA_EVENTMESSAGES (SENDERTIMESTAMP);

    create index IDX_HSEVMESSAGE_CRTDATE on HS_CA_EVENTMESSAGES (CRTDATE);

    create index IDX_HSEVMESSAGE_ISEXTENSION on HS_CA_EVENTMESSAGES (ISEXTENSION);

    create index IDX_HSEVMESSAGE_FKSECURITY on HS_CA_EVENTMESSAGES (FKSECURITY);

    create index IDX_HSEVMESSAGE_STATUS on HS_CA_EVENTMESSAGES (ISENDED, FKSTATE);

    create index IDX_HSEVMESSAGE_OPERATIONNORM on HS_CA_EVENTMESSAGES (OPERATIONNORM);

    alter table HS_CA_EVENTMESSAGES 
        add constraint FK_HSEVENTMESSAGE_EVENTTYPE 
        foreign key (FKEVENTTYPE) 
        references TB_CA_EVENTTYPES;

    alter table HS_CA_EVENTMESSAGES 
        add constraint FK_HSEVENTMESSAGE_SECURITY 
        foreign key (FKSECURITY) 
        references TB_SP_SECURITIES;

    alter table HS_CA_EVENTMESSAGES 
        add constraint FK_HSEVENTMESSAGE_PROVIDER 
        foreign key (FKPROVIDER) 
        references TB_CA_EVENTPROVIDERS;

    alter table HS_CA_EVENTMESSAGES 
        add constraint FK_HSEVENTMESSAGE_TYPE 
        foreign key (FKTYPE, FKFORMAT) 
        references TB_CA_MESSAGETYPES;

    alter table HS_CA_EVENTMESSAGES 
        add constraint FK56046059FC4CA9B4 
        foreign key (FKSTATE, FKSTATEFLOW) 
        references TB_STATES;

    create index IDX_HSEVENT_EVENTCLASS on HS_CA_EVENTS (EVENTCLASS);

    create index IDX_HSEVENT_REFID on HS_CA_EVENTS (REFID);

    create index IDX_HSEVENT_VERSION on HS_CA_EVENTS (VERSION);

    create index IDX_HSEVENT_FKEVENTDETAIL on HS_CA_EVENTS (FKEVENTDETAIL);

    create index IDX_HSEVENT_SECURITY on HS_CA_EVENTS (FKSECURITY);

    create index IDX_HSEVENT_STATUS on HS_CA_EVENTS (ISENDED, FKSTATEFLOW, FKSTATE);

    create index IDX_HSEVENT_FKMASTEREVENT on HS_CA_EVENTS (FKMASTEREVENT);

    create index IDX_HSEVENT_OPERDATE on HS_CA_EVENTS (OPERATIONALDATE);

    create index IDX_HSEVENT_DELETED on HS_CA_EVENTS (ISDELETED);

    create index IDX_HSEVENT_FKEVENTPROVIDER on HS_CA_EVENTS (FKEVENTPROVIDER);

    create index IDX_HSEVENT_FKEVENTGROUP on HS_CA_EVENTS (FKEVENTGROUP);

    create index IDX_HSEVENT_EVENTTYPE on HS_CA_EVENTS (FKEVENTTYPE);

    alter table HS_CA_EVENTS 
        add constraint FK_HSEVENT_EVENTTYPE 
        foreign key (FKEVENTTYPE) 
        references TB_CA_EVENTTYPES;

    alter table HS_CA_EVENTS 
        add constraint FK_HSCOLLECTED_GROUP 
        foreign key (FKEVENTGROUP) 
        references HS_CA_EVENTS;

    alter table HS_CA_EVENTS 
        add constraint FK_HSEVENT_SECURITY 
        foreign key (FKSECURITY) 
        references TB_SP_SECURITIES;

    alter table HS_CA_EVENTS 
        add constraint FK_HSEVENT_PROVIDER 
        foreign key (FKEVENTPROVIDER) 
        references TB_CA_EVENTPROVIDERS;

    alter table HS_CA_EVENTS 
        add constraint FK_HSEVENT_EVENTDETAIL 
        foreign key (FKEVENTDETAIL) 
        references HS_CA_EVENTDETAILS;

    alter table HS_CA_EVENTS 
        add constraint FK_HSGROUP_COLLECTED 
        foreign key (FKMASTEREVENT) 
        references HS_CA_EVENTS;

    alter table HS_CA_EVENTS 
        add constraint FK617DB5E6FC4CA9B4 
        foreign key (FKSTATE, FKSTATEFLOW) 
        references TB_STATES;

    create index IDX_HSSECPORT_DELETED on HS_SP_SECURITYPORTFOLIO (ISDELETED);

    alter table HS_SP_SECURITYPORTFOLIO 
        add constraint FK_HS_SECPORT_SECURITY 
        foreign key (FKSECURITY) 
        references TB_SP_SECURITIES;

    alter table HS_SP_SECURITYPORTFOLIO 
        add constraint FK_HS_SECPORT_CUSTOMER 
        foreign key (FKCUSTOMER) 
        references TB_SP_CUSTOMERS;

    create index IDX_PROCESS_CLASS on SDM_PROCESSES (PROCESSCLASS);

    create index IDX_PROCESSPROPERTY_PROCESS on SDM_PROCESSPROPERTIES (FKPROCESS);

    alter table SDM_PROCESSPROPERTIES 
        add constraint FK_PROCESSPROPERTY_PROCESS 
        foreign key (FKPROCESS) 
        references SDM_PROCESSES;

    create index IDX_TASKEXECUTION_TASK on SDM_TASKEXECUTIONS (FKTASK);

    create index IDX_TASKEXECUTION_VERSION on SDM_TASKEXECUTIONS (VERSION);

    create index IDX_TASKEXECUTION_STATUS on SDM_TASKEXECUTIONS (ISENDED, FKSTATEFLOW, FKSTATE);

    alter table SDM_TASKEXECUTIONS 
        add constraint FK_EXECUTION_TASK 
        foreign key (FKTASK) 
        references SDM_TASKS;

    alter table SDM_TASKEXECUTIONS 
        add constraint FK606D8223FC4CA9B4 
        foreign key (FKSTATE, FKSTATEFLOW) 
        references TB_STATES;

    create index IDX_TASK_STATUS on SDM_TASKS (ISENDED, FKSTATEFLOW, FKSTATE);

    create index IDX_TASK_PROCESS on SDM_TASKS (FKPROCESS);

    create index IDX_TASK_CLASS on SDM_TASKS (TASKCLASS);

    create index IDX_TASK_VERSION on SDM_TASKS (VERSION);

    alter table SDM_TASKS 
        add constraint FK_TASK_PROCESS 
        foreign key (FKPROCESS) 
        references SDM_PROCESSES;

    alter table SDM_TASKS 
        add constraint FK3A1AA18BFC4CA9B4 
        foreign key (FKSTATE, FKSTATEFLOW) 
        references TB_STATES;

    alter table TB_ANSWERS 
        add constraint FK_POLL_ANSWER 
        foreign key (POLL_ID) 
        references TB_POLL;

    alter table TB_CATEGORIES 
        add constraint FKE9DA4C8D6F263114 
        foreign key (MASTER_CONTENT_ID) 
        references TB_CONTENT;

    alter table TB_CATEGORIES 
        add constraint FK_CATEGORY_PARENT 
        foreign key (PARENT_ID) 
        references TB_CATEGORIES;

    alter table TB_CATEGORY_PERMISSION 
        add constraint FK_CATEGORY_PERMISSION 
        foreign key (CATEGORY_ID) 
        references TB_CATEGORIES;

    alter table TB_CA_DOMAINCLUSTERFORMATS 
        add constraint FK_DOMCLSFORM_CLUSTER 
        foreign key (FKDOMAINCLUSTER, FKAPPLICATION, FKDOMAIN) 
        references TB_DOMAINCLUSTERS;

    alter table TB_CA_DOMAINCLUSTERFORMATS 
        add constraint FK_DOMCLSFORM_FORMAT 
        foreign key (FKMESSAGEFORMAT) 
        references TB_CA_MESSAGEFORMATS;

    create index IDX_EVCONFFLD_FKEVTYPEDET on TB_CA_EVENTCONFIGFIELDS (FKEVENTTYPEDETAIL);

    create index IDX_EVCONFFLD_FKEVCONF on TB_CA_EVENTCONFIGFIELDS (FKEVENTCONFIG);

    create index IDX_EVCONFFLD_DELETED on TB_CA_EVENTCONFIGFIELDS (ISDELETED);

    alter table TB_CA_EVENTCONFIGFIELDS 
        add constraint FK_EVNCONFFIELD_EVENTCONFIG 
        foreign key (FKEVENTCONFIG) 
        references TB_CA_EVENTCONFIGS;

    alter table TB_CA_EVENTCONFIGFIELDS 
        add constraint FK_EVNCONFFIELD_EVNTYPEDET 
        foreign key (FKEVENTTYPEDETAIL) 
        references TB_CA_EVENTTYPEDETAILS;

    create index IDX_EVCONFMSGFLD_FKEVFILDCONF on TB_CA_EVENTCONFIGMSGFLDS (FKEVENTFIELDCONFIG);

    create index IDX_EVCONFMSGFLD_DELETED on TB_CA_EVENTCONFIGMSGFLDS (ISDELETED);

    create index IDX_EVCONFMSGFLD_FKMESSAGETYPE on TB_CA_EVENTCONFIGMSGFLDS (FKFORMAT, FKMSGTYPE);

    create index IDX_EVCONFMSGFLD_FKEVPROVIDER on TB_CA_EVENTCONFIGMSGFLDS (FKEVENTPROVIDER);

    alter table TB_CA_EVENTCONFIGMSGFLDS 
        add constraint FK_EVNCONFFLDPRV_EVENTPROV 
        foreign key (FKEVENTPROVIDER) 
        references TB_CA_EVENTPROVIDERS;

    alter table TB_CA_EVENTCONFIGMSGFLDS 
        add constraint FK_EVNCONFFLDPRV_EVNCONFFLD 
        foreign key (FKEVENTFIELDCONFIG) 
        references TB_CA_EVENTCONFIGFIELDS;

    alter table TB_CA_EVENTCONFIGMSGFLDS 
        add constraint FK_EVNCONFFLDMT_EVENTMESG 
        foreign key (FKMSGTYPE, FKFORMAT) 
        references TB_CA_MESSAGETYPES;

    create index IDX_EVCONFIG_DELETED on TB_CA_EVENTCONFIGS (ISDELETED);

    alter table TB_CA_EVENTCONFIGS 
        add constraint FK_EVENTCONFIG_EVENTTYPE 
        foreign key (FKEVENTTYPE) 
        references TB_CA_EVENTTYPES;

    alter table TB_CA_EVENTCONFIGS 
        add constraint FK_EVENTCONFIG_EVENTPROVIDER2 
        foreign key (FKSECPREFPROV2) 
        references TB_CA_EVENTPROVIDERS;

    alter table TB_CA_EVENTCONFIGS 
        add constraint FK_EVENTCONFIG_EVENTPROVIDER1 
        foreign key (FKPREFPROVIDER1) 
        references TB_CA_EVENTPROVIDERS;

    create index IDX_EVDETAIL_VERSION on TB_CA_EVENTDETAILS (VERSION);

    create index IDX_EVDETAIL_OPERDATE on TB_CA_EVENTDETAILS (OPERATIONALDATE);

    create index IDX_EVDETAIL_DELETED on TB_CA_EVENTDETAILS (ISDELETED);

    create index IDX_EVDETAIL_REFID on TB_CA_EVENTDETAILS (REFID);

    create index IDX_EVTEXTDETAIL_REFID on TB_CA_EVENTEXTDETAILS (REFID);

    create index IDX_EVTEXTDETAIL_VERSION on TB_CA_EVENTEXTDETAILS (VERSION);

    create index IDX_EVTEXTDETAIL_FKEVENTDETAIL on TB_CA_EVENTEXTDETAILS (FKEVENTDETAIL);

    create index IDX_EVTEXTDETAIL_DELETED on TB_CA_EVENTEXTDETAILS (ISDELETED);

    create index IDX_EVTEXTDETAIL_EXTENSIONID on TB_CA_EVENTEXTDETAILS (EXTENSIONNAME, EXTENSIONNUMBER);

    alter table TB_CA_EVENTEXTDETAILS 
        add constraint FK_EXTENDED_DETAIL 
        foreign key (FKEVENTDETAIL) 
        references TB_CA_EVENTDETAILS;

    create index IDX_EVHOLANSW_VERSION on TB_CA_EVENTHOLDINGANSWER (VERSION);

    create index IDX_EVHOLANSW_DELETED on TB_CA_EVENTHOLDINGANSWER (ISDELETED);

    alter table TB_CA_EVENTHOLDINGANSWER 
        add constraint FK_QUESTION 
        foreign key (FKQUESTION) 
        references TB_CA_QUESTIONS;

    alter table TB_CA_EVENTHOLDINGANSWER 
        add constraint FK_HOLDING 
        foreign key (FKHOLDING) 
        references TB_SP_SECURITYPORTFOLIO;

    create index IDX_EVMESGFLD_FKEVENTMESSAGE on TB_CA_EVENTMESSAGEFIELDS (FKEVENTMESSAGE);

    alter table TB_CA_EVENTMESSAGEFIELDS 
        add constraint FK_EVENTFIELD_MESSAGE 
        foreign key (FKEVENTMESSAGE) 
        references TB_CA_EVENTMESSAGES;

    create index IDX_EVMESSAGE_MESSAGEID on TB_CA_EVENTMESSAGES (MESSAGEID);

    create index IDX_EVMESSAGE_DELETED on TB_CA_EVENTMESSAGES (ISDELETED);

    create index IDX_EVMESSAGE_FKEVENTTYPE on TB_CA_EVENTMESSAGES (FKEVENTTYPE);

    create index IDX_EVMESSAGE_OPERATIONNORM on TB_CA_EVENTMESSAGES (OPERATIONNORM);

    create index IDX_EVMESSAGE_FKEVENT on TB_CA_EVENTMESSAGES (FKEVENT);

    create index IDX_EVMESSAGE_SENDTIMESTAMP on TB_CA_EVENTMESSAGES (SENDERTIMESTAMP);

    create index IDX_EVMESSAGE_FKSECURITY on TB_CA_EVENTMESSAGES (FKSECURITY);

    create index IDX_EVMESSAGE_EXTENSIONREF on TB_CA_EVENTMESSAGES (EXTENSIONREF);

    create index IDX_EVMESSAGE_ISINPORTFOLIO on TB_CA_EVENTMESSAGES (ISINPORTFOLIO);

    create index IDX_EVMESSAGE_STATUS on TB_CA_EVENTMESSAGES (ISENDED, FKSTATE);

    create index IDX_EVMESSAGE_PROVIDER on TB_CA_EVENTMESSAGES (FKPROVIDER);

    create index IDX_EVMESSAGE_EFFECTIVEDATE on TB_CA_EVENTMESSAGES (EFFECTIVEDATE);

    create index IDX_EVMESSAGE_FKFORMAT on TB_CA_EVENTMESSAGES (FKFORMAT);

    create index IDX_EVMESSAGE_CRTDATE on TB_CA_EVENTMESSAGES (CRTDATE);

    create index IDX_EVMESSAGE_ISEXTENSION on TB_CA_EVENTMESSAGES (ISEXTENSION);

    alter table TB_CA_EVENTMESSAGES 
        add constraint FK_EVENTMESSAGE_EVENTTYPE 
        foreign key (FKEVENTTYPE) 
        references TB_CA_EVENTTYPES;

    alter table TB_CA_EVENTMESSAGES 
        add constraint FK_EVENTMESSAGE_SECURITY 
        foreign key (FKSECURITY) 
        references TB_SP_SECURITIES;

    alter table TB_CA_EVENTMESSAGES 
        add constraint FK_EVENTMESSAGE_PROVIDER 
        foreign key (FKPROVIDER) 
        references TB_CA_EVENTPROVIDERS;

    alter table TB_CA_EVENTMESSAGES 
        add constraint FK_EVENTMESSAGE_TYPE 
        foreign key (FKTYPE, FKFORMAT) 
        references TB_CA_MESSAGETYPES;

    alter table TB_CA_EVENTMESSAGES 
        add constraint FKEC50F156FC4CA9B4 
        foreign key (FKSTATE, FKSTATEFLOW) 
        references TB_STATES;

    alter table TB_CA_EVENTMRCONFIGS 
        add constraint FK_EVENTMRCONFIGS_EVENTCONFIG 
        foreign key (FKEVENTCONFIG) 
        references TB_CA_EVENTCONFIGS;

    alter table TB_CA_EVENTMRCONFIGS 
        add constraint FK_EVENTMRCONFIGS_EVNTYPEDET 
        foreign key (FKEVENTTYPEDETAIL) 
        references TB_CA_EVENTTYPEDETAILS;

    create index IDX_EVPROVIDER_DELETED on TB_CA_EVENTPROVIDERS (ISDELETED);

    create index IDX_EVPROVIDER_EVENTPROVCLASS on TB_CA_EVENTPROVIDERS (EVENTPROVCLASS);

    create index IDX_EVENT_STATUS on TB_CA_EVENTS (ISENDED, FKSTATEFLOW, FKSTATE);

    create index IDX_EVENT_OPERDATE on TB_CA_EVENTS (OPERATIONALDATE);

    create index IDX_EVENT_DELETED on TB_CA_EVENTS (ISDELETED);

    create index IDX_EVENT_FKMASTEREVENT on TB_CA_EVENTS (FKMASTEREVENT);

    create index IDX_EVENT_FKEVENTGROUP on TB_CA_EVENTS (FKEVENTGROUP);

    create index IDX_EVENT_SECURITY on TB_CA_EVENTS (FKSECURITY);

    create index IDX_EVENT_EVENTCLASS on TB_CA_EVENTS (EVENTCLASS);

    create index IDX_EVENT_REFID on TB_CA_EVENTS (REFID);

    create index IDX_EVENT_FKEVENTDETAIL on TB_CA_EVENTS (FKEVENTDETAIL);

    create index IDX_EVENT_FKEVENTPROVIDER on TB_CA_EVENTS (FKEVENTPROVIDER);

    create index IDX_EVENT_EVENTTYPE on TB_CA_EVENTS (FKEVENTTYPE);

    create index IDX_EVENT_VERSION on TB_CA_EVENTS (VERSION);

    alter table TB_CA_EVENTS 
        add constraint FK_EVENT_EVENTTYPE 
        foreign key (FKEVENTTYPE) 
        references TB_CA_EVENTTYPES;

    alter table TB_CA_EVENTS 
        add constraint FK_COLLECTED_GROUP 
        foreign key (FKEVENTGROUP) 
        references TB_CA_EVENTS;

    alter table TB_CA_EVENTS 
        add constraint FK_EVENT_SECURITY 
        foreign key (FKSECURITY) 
        references TB_SP_SECURITIES;

    alter table TB_CA_EVENTS 
        add constraint FK_EVENT_PROVIDER 
        foreign key (FKEVENTPROVIDER) 
        references TB_CA_EVENTPROVIDERS;

    alter table TB_CA_EVENTS 
        add constraint FK_EVENT_EVENTDETAIL 
        foreign key (FKEVENTDETAIL) 
        references TB_CA_EVENTDETAILS;

    alter table TB_CA_EVENTS 
        add constraint FK_GROUP_COLLECTED 
        foreign key (FKMASTEREVENT) 
        references TB_CA_EVENTS;

    alter table TB_CA_EVENTS 
        add constraint FKDAA19789FC4CA9B4 
        foreign key (FKSTATE, FKSTATEFLOW) 
        references TB_STATES;

    create index IDX_EVTYPEDET_DELETED on TB_CA_EVENTTYPEDETAILS (ISDELETED);

    create index IDX_EVTYPEDET_ISDISPLAYINTABLE on TB_CA_EVENTTYPEDETAILS (ISDISPLAYINTABLE);

    create index IDX_EVTYPEDET_FIELDPATH on TB_CA_EVENTTYPEDETAILS (FIELDPATH);

    create index IDX_EVTYPEDET_FKEVENTTYPE on TB_CA_EVENTTYPEDETAILS (FKEVENTTYPE);

    alter table TB_CA_EVENTTYPEDETAILS 
        add constraint FK_DETAIL_EVENTTYPE 
        foreign key (FKEVENTTYPE) 
        references TB_CA_EVENTTYPES;

    create index IDX_FORMATPROV_FKPROVIDER on TB_CA_FORMATPROVIDERS (FKEVENTPROVIDER);

    create index IDX_FORMATPROV_FKFORMAT on TB_CA_FORMATPROVIDERS (FKMESSAGEFORMAT, IDATMESSAGE);

    alter table TB_CA_FORMATPROVIDERS 
        add constraint FK_FORMATPROV_FORMAT 
        foreign key (FKMESSAGEFORMAT) 
        references TB_CA_MESSAGEFORMATS;

    alter table TB_CA_FORMATPROVIDERS 
        add constraint FK_FORMATPROV_PROVIDER 
        foreign key (FKEVENTPROVIDER) 
        references TB_CA_EVENTPROVIDERS;

    alter table TB_CA_MESSAGEFIELDCONFIGS 
        add constraint FK_FIELD_TYPE 
        foreign key (FKTYPE, FKFORMAT) 
        references TB_CA_MESSAGETYPES;

    create index IDX_MESGFORMAT_DELETED on TB_CA_MESSAGEFORMATS (ISDELETED);

    alter table TB_CA_MESSAGETYPES 
        add constraint FK_TYPE_FORMAT 
        foreign key (FKFORMAT) 
        references TB_CA_MESSAGEFORMATS;

    create index IDX_QUESTION_VERSION on TB_CA_QUESTIONS (VERSION);

    create index IDX_QUESTION_DELETED on TB_CA_QUESTIONS (ISDELETED);

    create index IDX_QUESTION_EVENT on TB_CA_QUESTIONS (FKEVENT);

    alter table TB_CA_QUESTIONS 
        add constraint FK_ANSWERTYPE 
        foreign key (FKANSWERTYPE) 
        references TB_CA_ANSWERTYPES;

    alter table TB_CA_QUESTIONS 
        add constraint FK_QUESTIONEVENT 
        foreign key (FKEVENT) 
        references TB_CA_EVENTS;

    alter table TB_CONFIGS 
        add constraint FK_CONFIG_APPLICATION 
        foreign key (FKAPPLICATION) 
        references TB_APPLICATIONS;

    alter table TB_CONTAINER_SCHEDULE 
        add constraint FK_SCHEDULE_HTML 
        foreign key (CONTENT_ID) 
        references TB_CONTENT;

    alter table TB_CONTAINER_SCHEDULE 
        add constraint FK_CONTAINER_SCHEDULE 
        foreign key (CONTAINER_NAME, CONTAINER_TYPE) 
        references TB_CONTAINER;

    alter table TB_CONTENT 
        add constraint FK_MASTER_CONTENT 
        foreign key (MASTER_CONTENT_ID) 
        references TB_CONTENT;

    alter table TB_CONTENT 
        add constraint FK_CONTENT_STATUS 
        foreign key (STATUS_ID) 
        references TB_CONTENT_STATUS;

    alter table TB_CONTENTS_CATEGORIES 
        add constraint FK_CONTENTS_CATEGORIES 
        foreign key (CONTENT_ID) 
        references TB_CONTENT;

    alter table TB_CONTENTS_CATEGORIES 
        add constraint FK_CATEGORIES_CONTENTS 
        foreign key (CATEGORY_ID) 
        references TB_CATEGORIES;

    alter table TB_CONTENT_PERMISSION 
        add constraint FK_CONTENT_PERMISSION 
        foreign key (CONTENT_ID) 
        references TB_CONTENT;

    alter table TB_DIARY 
        add constraint FK_DIARY_CONTENT 
        foreign key (CONTENT_ID) 
        references TB_CONTENT;

    alter table TB_DOCUMENTS 
        add constraint FK_DOCUMENT_CONTENT 
        foreign key (CONTENT_ID) 
        references TB_CONTENT;

    alter table TB_DOCUMENT_VERSIONS 
        add constraint FK_VERSION_DOC 
        foreign key (DOCUMENT_ID) 
        references TB_DOCUMENTS;

    alter table TB_DOCUMENT_VERSIONS 
        add constraint FK_DOC_ATTACHMENT 
        foreign key (ATTACHED_TO) 
        references TB_DOCUMENT_VERSIONS;

    alter table TB_DOCUMENT_VERSIONS 
        add constraint FK_DOC_VERSION_CONTENT 
        foreign key (CONTENT_ID) 
        references TB_CONTENT;

    alter table TB_DOCUMENT_VERSIONS 
        add constraint FK_VERSION_MIME_TYPE 
        foreign key (MIME_TYPE) 
        references TB_MIME_TYPES;

    alter table TB_DOMAINCLUSTERS 
        add constraint FK_DOMAINCLUSTER_DOMAIN 
        foreign key (FKAPPLICATION, FKDOMAIN) 
        references TB_DOMAINS;

    alter table TB_DOMAINNORMS 
        add constraint FK_DOMAINNORM_DOMAIN 
        foreign key (FKAPPLICATION, FKDOMAIN) 
        references TB_DOMAINS;

    alter table TB_DOMAINS 
        add constraint FK_DOMAIN_APPLICATION 
        foreign key (FKAPPLICATION) 
        references TB_APPLICATIONS;

    alter table TB_DOMAINVALUES 
        add constraint FK_VALUE_NORM 
        foreign key (FKNORM, FKNRMAPPLICATION, FKNRMDOMAIN) 
        references TB_DOMAINNORMS;

    alter table TB_DOMAINVALUES 
        add constraint FK_VALUE_CLUSTER 
        foreign key (FKCLUSTER, FKCLTAPPLICATION, FKCLTDOMAIN) 
        references TB_DOMAINCLUSTERS;

    create index IDX_STATE_FKAPPLICATION on TB_FLOWS (FKAPPLICATION);

    alter table TB_FLOWS 
        add constraint FK_FLOW_APPLICATION 
        foreign key (FKAPPLICATION) 
        references TB_APPLICATIONS;

    alter table TB_FUNCTIONS 
        add constraint FK_FUNCTION_MODULE 
        foreign key (MODULE_ID) 
        references TB_MODULES;

    alter table TB_FUNCTION_GROUPS 
        add constraint FK_FUNCTION_GROUP 
        foreign key (GROUP_ID) 
        references TB_GROUPS;

    alter table TB_FUNCTION_GROUPS 
        add constraint FK_GROUP_FUNCTION 
        foreign key (FUNCTION_ID, MODULE_ID) 
        references TB_FUNCTIONS;

    alter table TB_GROUPS 
        add constraint FK_GROUP_TYPE 
        foreign key (GROUP_TYPE) 
        references TB_GROUP_TYPES;

    alter table TB_HTML 
        add constraint FK_HTML_CONTENT 
        foreign key (CONTENT_ID) 
        references TB_CONTENT;

    create index IDX_LOGFIELDUPDATE_FKLOG on TB_LOGFIELDUPDATES (FKLOG);

    alter table TB_LOGFIELDUPDATES 
        add constraint FK_LOGFIELDUPDATE_LOG 
        foreign key (FKLOG) 
        references TB_LOGS;

    create index IDX_LOG_CRTDATE on TB_LOGS (CRTDATE);

    create index IDX_LOG_LOGCLASS on TB_LOGS (LOGCLASS);

    create index IDX_LOG_LOGLEVEL on TB_LOGS (LOGLEVEL);

    alter table TB_MENU_ITEMS 
        add constraint FK_MENUITEMS_BOXLET 
        foreign key (CONTAINER_NAME, CONTAINER_TYPE) 
        references TB_CONTAINER;

    alter table TB_MENU_ITEMS 
        add constraint FK_MENUITEMS_CATEGORY 
        foreign key (CATEGORY_ID) 
        references TB_CATEGORIES;

    alter table TB_MENU_ITEMS 
        add constraint FKCD87B7318D188391 
        foreign key (PARENT) 
        references TB_MENU_ITEMS;

    alter table TB_MENU_ITEMS 
        add constraint FKCD87B7317E940B5E 
        foreign key (PARENT) 
        references TB_MENU_ITEMS;

    alter table TB_MENU_ITEM_GROUP 
        add constraint FK41D94542CA0DE70E 
        foreign key (GROUP_ID) 
        references TB_GROUPS;

    alter table TB_MENU_ITEM_GROUP 
        add constraint FK41D94542844EA99B 
        foreign key (MENU_ITEM_ID) 
        references TB_MENU_ITEMS;

    alter table TB_MESSAGE 
        add constraint FK_USER_MSG_CONTENT 
        foreign key (CONTENT_ID) 
        references TB_CONTENT;

    alter table TB_MODULES 
        add constraint FK_MODULE_TYPE 
        foreign key (MODULE_TYPE) 
        references TB_MODULE_TYPES;

    alter table TB_NEWS 
        add constraint FK_NEWS_CONTENT 
        foreign key (CONTENT_ID) 
        references TB_CONTENT;

    alter table TB_NEWS_LINK 
        add constraint FK_NEWS_LINK 
        foreign key (NEWS_ID) 
        references TB_NEWS;

    alter table TB_NEWS_PICTURE 
        add constraint FK_NEWS_PICTURE 
        foreign key (NEWS_ID) 
        references TB_NEWS;

    alter table TB_OLD_PASSWORDS 
        add constraint FK_USER_OLD_PSWD 
        foreign key (USER_ID) 
        references TB_USER_PROFILES;

    alter table TB_PAGES 
        add constraint FK_SESSION_PAGES 
        foreign key (TRACKING_ID) 
        references TB_TRACKING;

    alter table TB_POLL 
        add constraint FK_POLL_CONTENT 
        foreign key (CONTENT_ID) 
        references TB_CONTENT;

    alter table TB_POLL_USER 
        add constraint FK_POLL_USER 
        foreign key (POLL_ID) 
        references TB_POLL;

    alter table TB_POPUP_SCHEDULE 
        add constraint FK_POPUP_SCHEDULE 
        foreign key (SCHEDULE_ID) 
        references TB_CONTAINER_SCHEDULE;

    create index IDX_EVMESGFLD_FKASSETMESSAGE on TB_SDM_ASSETMESSAGEFIELDS (FKASSETMESSAGE);

    alter table TB_SDM_ASSETMESSAGEFIELDS 
        add constraint FK_ASSETFIELD_MESSAGE 
        foreign key (FKASSETMESSAGE) 
        references TB_SDM_ASSETMESSAGES;

    create index IDX_ASSETMESSAGE_ISEXTENSION on TB_SDM_ASSETMESSAGES (ISEXTENSION);

    create index IDX_ASSETMESSAGE_STATUS on TB_SDM_ASSETMESSAGES (ISENDED, FKSTATE);

    create index IDX_ASSETMESSAGE_ISINPORTFOLIO on TB_SDM_ASSETMESSAGES (ISINPORTFOLIO);

    create index IDX_ASSETMESSAGE_DELETED on TB_SDM_ASSETMESSAGES (ISDELETED);

    create index IDX_ASSETMESSAGE_PROVIDER on TB_SDM_ASSETMESSAGES (FKPROVIDER);

    create index IDX_ASSETMESSAGE_FKASSET on TB_SDM_ASSETMESSAGES (FKASSET);

    create index IDX_ASSETMESSAGE_FKASSETTYPE on TB_SDM_ASSETMESSAGES (FKASSETTYPE);

    create index IDX_ASSETMESSAGE_SENDTIMESTAMP on TB_SDM_ASSETMESSAGES (SENDERTIMESTAMP);

    create index IDX_ASSETMESSAGE_CRTDATE on TB_SDM_ASSETMESSAGES (CRTDATE);

    create index IDX_ASSETMESSAGE_MESSAGEID on TB_SDM_ASSETMESSAGES (MESSAGEID);

    alter table TB_SDM_ASSETMESSAGES 
        add constraint FK_ASSETMESSAGE_ASSETTYPE 
        foreign key (FKASSETTYPE) 
        references TB_SDM_ASSETTYPES;

    alter table TB_SDM_ASSETMESSAGES 
        add constraint FK_ASSETMESSAGE_PROVIDER 
        foreign key (FKPROVIDER) 
        references TB_SDM_PROVIDERS;

    alter table TB_SDM_ASSETMESSAGES 
        add constraint FKC8A739A8FC4CA9B4 
        foreign key (FKSTATE, FKSTATEFLOW) 
        references TB_STATES;

    create index IX_FK_ASSET_PROVIDER on TB_SDM_ASSETS (FKASSETPROVIDER);

    create index IX_ASSET_FKASSETTYPE on TB_SDM_ASSETS (FKASSETTYPE);

    create index IX_FK_ASSET_ASSETDETAIL on TB_SDM_ASSETS (FKASSETDETAIL);

    alter table TB_SDM_ASSETS 
        add constraint FK_ASSET_ASSETTYPE 
        foreign key (FKASSETTYPE) 
        references TB_SDM_ASSETTYPES;

    alter table TB_SDM_ASSETS 
        add constraint FK_ASSET_PROVIDER 
        foreign key (FKASSETPROVIDER) 
        references TB_SDM_PROVIDERS;

    alter table TB_SDM_ASSETS 
        add constraint FK69742777FC4CA9B4 
        foreign key (FKSTATE, FKSTATEFLOW) 
        references TB_STATES;

    alter table TB_SDM_ASSETS 
        add constraint FK_ASSET_ASSETDETAIL 
        foreign key (FKASSETDETAIL) 
        references TB_SDM_ASSETDETAILS;

    create index IX_FKMASTERASSETDYNAMIC on TB_SDM_ASSETSDYNAMIC (FKMASTERASSET);

    create index IX_FK_ASSETDYNAMIC_PROVIDER on TB_SDM_ASSETSDYNAMIC (FKASSETPROVIDER);

    create index IX_FKASSETDYNAMICGROUP on TB_SDM_ASSETSDYNAMIC (FKASSETGROUP);

    create index IX_ASSETDYNAMIC_FKASSETTYPE on TB_SDM_ASSETSDYNAMIC (FKASSETTYPE);

    create index IX_FK_ASSETDYNAMIC_ASSETDETAIL on TB_SDM_ASSETSDYNAMIC (FKASSETDETAIL);

    alter table TB_SDM_ASSETSDYNAMIC 
        add constraint FK_ASSETDYNAMIC_ASSETTYPE 
        foreign key (FKASSETTYPE) 
        references TB_SDM_ASSETTYPES;

    alter table TB_SDM_ASSETSDYNAMIC 
        add constraint FK_ASSETDYNAMICCOLLECTED_GROUP 
        foreign key (FKASSETGROUP) 
        references TB_SDM_ASSETSDYNAMIC;

    alter table TB_SDM_ASSETSDYNAMIC 
        add constraint FK_ASSETDYNAMIC_PROVIDER 
        foreign key (FKASSETPROVIDER) 
        references TB_SDM_PROVIDERS;

    alter table TB_SDM_ASSETSDYNAMIC 
        add constraint FK_GROUP_ASSETDYNAMICCOLLECTED 
        foreign key (FKMASTERASSET) 
        references TB_SDM_ASSETSDYNAMIC;

    alter table TB_SDM_ASSETSDYNAMIC 
        add constraint FKCFEC0C8FC4CA9B4 
        foreign key (FKSTATE, FKSTATEFLOW) 
        references TB_STATES;

    alter table TB_SDM_ASSETSDYNAMIC 
        add constraint FK_ASSETDYNAMIC_ASSETDETAIL 
        foreign key (FKASSETDETAIL) 
        references TB_SDM_ASSETDYNAMICDETAILS;

    create index IX_ASSETSTATIC_FKASSETTYPE on TB_SDM_ASSETSSTATIC (FKASSETTYPE);

    create index IX_FKASSETSTATICGROUP on TB_SDM_ASSETSSTATIC (FKASSETGROUP);

    create index IX_FK_ASSETSTATIC_ASSETDETAIL on TB_SDM_ASSETSSTATIC (FKASSETDETAIL);

    create index IX_FK_ASSETSTATIC_PROVIDER on TB_SDM_ASSETSSTATIC (FKASSETPROVIDER);

    create index IX_FKMASTERASSETSTATIC on TB_SDM_ASSETSSTATIC (FKMASTERASSET);

    alter table TB_SDM_ASSETSSTATIC 
        add constraint FK_ASSETSTATIC_ASSETTYPE 
        foreign key (FKASSETTYPE) 
        references TB_SDM_ASSETTYPES;

    alter table TB_SDM_ASSETSSTATIC 
        add constraint FK_ASSETSTATICCOLLECTED_GROUP 
        foreign key (FKASSETGROUP) 
        references TB_SDM_ASSETSSTATIC;

    alter table TB_SDM_ASSETSSTATIC 
        add constraint FK_ASSETSTATIC_PROVIDER 
        foreign key (FKASSETPROVIDER) 
        references TB_SDM_PROVIDERS;

    alter table TB_SDM_ASSETSSTATIC 
        add constraint FK_GROUP_ASSETSTATICCOLLECTED 
        foreign key (FKMASTERASSET) 
        references TB_SDM_ASSETSSTATIC;

    alter table TB_SDM_ASSETSSTATIC 
        add constraint FKBEE134A5FC4CA9B4 
        foreign key (FKSTATE, FKSTATEFLOW) 
        references TB_STATES;

    alter table TB_SDM_ASSETSSTATIC 
        add constraint FK_ASSETSTATIC_ASSETDETAIL 
        foreign key (FKASSETDETAIL) 
        references TB_SDM_ASSETSTATICDETAILS;

    create index IDX_ASSETTYPEDETAIL_NAME on TB_SDM_ASSETTYPE_DETAILS (NAME);

    create index IDX_ASSETTYPEDETAIL_ASSETTYPE on TB_SDM_ASSETTYPE_DETAILS (FKASSETTYPE);

    create index IDX_ASSETTYPEDETAIL_NATURE on TB_SDM_ASSETTYPE_DETAILS (NATURE);

    alter table TB_SDM_ASSETTYPE_DETAILS 
        add constraint FK_ASSETTYPEDETAIL_ASSETTYPE 
        foreign key (FKASSETTYPE) 
        references TB_SDM_ASSETTYPES;

    alter table TB_SDM_ASSETTYPE_DETAILS 
        add constraint FK_ASSETTYPEDETAIL_ENTERPRISE 
        foreign key (FKENTERPRISE) 
        references TB_SDM_ENTERPRISE;

    create index IX_DETAILBLK_ASSETDETAIL on TB_SDM_ASSETTYPE_DETAILS_BLK (FKASSETTYPEDETAIL);

    create index IX_DETAILBLK_PROVIDER on TB_SDM_ASSETTYPE_DETAILS_BLK (FKPROVIDER);

    alter table TB_SDM_ASSETTYPE_DETAILS_BLK 
        add constraint FK_DETAILBLK_ASSETDETAIL 
        foreign key (FKASSETTYPEDETAIL) 
        references TB_SDM_ASSETTYPE_DETAILS;

    alter table TB_SDM_ASSETTYPE_DETAILS_BLK 
        add constraint FK_DETAILBLK_PROVIDER 
        foreign key (FKPROVIDER) 
        references TB_SDM_PROVIDERS;

    alter table TB_SDM_ENTERPRISE 
        add constraint FK_ENT_PARENT 
        foreign key (FKENTPARENT) 
        references TB_SDM_ENTERPRISE;

    alter table TB_SDM_ERROR_VALUES 
        add constraint FK_ERRORVALUE_VALUE 
        foreign key (FKVALUE) 
        references TB_SDM_VALUES;

    create index IDX_SDMFIELDS_NAME on TB_SDM_FIELDS (NAME);

    create index IDX_SDMFIELDS_JOBTYPE on TB_SDM_FIELDS (FKJOBTYPE);

    alter table TB_SDM_FIELDS 
        add constraint FK_JOBFIELD_JOBTYPE 
        foreign key (FKJOBTYPE) 
        references TB_SDM_JOBTYPE;

    alter table TB_SDM_JOBS 
        add constraint FK_JOB_JOBTYPE 
        foreign key (FKJOBTYPE) 
        references TB_SDM_JOBTYPE;

    create index IX_FK_JOBTYPE_FIELD on TB_SDM_JOBTYPE_FIELDS (FKJOBTYPE);

    alter table TB_SDM_JOBTYPE_FIELDS 
        add constraint FK_JOBTYPE 
        foreign key (FKJOBTYPE) 
        references TB_SDM_JOBTYPE;

    alter table TB_SDM_MESSAGEFIELDCONFIGS 
        add constraint FK_FIELD_MESSAGETYPE 
        foreign key (FKASSETTYPE, FKPROVIDER) 
        references TB_SDM_MESSAGETYPES;

    create index IX_MESSAGETYPE_PROVIDER on TB_SDM_MESSAGETYPES (FKPROVIDER);

    create index IX_MESSAGETYPE_ASSETTYPE on TB_SDM_MESSAGETYPES (FKASSETTYPE);

    alter table TB_SDM_MESSAGETYPES 
        add constraint FK_MESSAGETYPE_ASSETTYPE 
        foreign key (FKASSETTYPE) 
        references TB_SDM_ASSETTYPES;

    alter table TB_SDM_MESSAGETYPES 
        add constraint FK_MESSAGETYPE_PROVIDER 
        foreign key (FKPROVIDER) 
        references TB_SDM_PROVIDERS;

    create index IDX_NORMALIZATION_NAME on TB_SDM_NORMALIZATIONS (NAME);

    alter table TB_SDM_NORMALIZATION_FILTERS 
        add constraint FK_NORM 
        foreign key (FKNORMALIZATION) 
        references TB_SDM_NORMALIZATIONS;

    alter table TB_SDM_ROWS 
        add constraint FK_ROW_JOB 
        foreign key (FKJOB) 
        references TB_SDM_JOBS;

    alter table TB_SDM_ROWS 
        add constraint FK1F4EA6CDFC4CA9B4 
        foreign key (FKSTATE, FKSTATEFLOW) 
        references TB_STATES;

    alter table TB_SDM_SECEVENTS 
        add constraint FK_ASSETTYPE_SECURITY 
        foreign key (FKASSETTYPE) 
        references TB_SDM_ASSETTYPES;

    alter table TB_SDM_SECEVENTS 
        add constraint FK_SECEVENT_MARKET 
        foreign key (FKMARKET) 
        references TB_SP_MARKETS;

    alter table TB_SDM_SECEVENTS 
        add constraint FK342F3816FC4CA9B4 
        foreign key (FKSTATE, FKSTATEFLOW) 
        references TB_STATES;

    create index IDX_STCONFFLD_FKSTCONF on TB_SDM_STCONFIGFIELDS (FKSTATICCONFIG);

    create index IDX_STCONFFLD_DELETED on TB_SDM_STCONFIGFIELDS (ISDELETED);

    create index IDX_STCONFFLD_FKASSTYPEDET on TB_SDM_STCONFIGFIELDS (FKASSETTYPEDETAIL);

    alter table TB_SDM_STCONFIGFIELDS 
        add constraint FK_ASSCONFFIELD_ASSTYPEDET 
        foreign key (FKASSETTYPEDETAIL) 
        references TB_SDM_ASSETTYPE_DETAILS;

    alter table TB_SDM_STCONFIGFIELDS 
        add constraint FK_ASSCONFFIELD_STATICCONFIG 
        foreign key (FKSTATICCONFIG) 
        references TB_SDM_STCONFIGS;

    create index IDX_STCONFMSGFLD_FKSTPROVIDER on TB_SDM_STCONFIGMSGFLDS (FKPROVIDER);

    create index IDX_STCONFMSGFLD_FKASSTYPE on TB_SDM_STCONFIGMSGFLDS (FKASSETTYPE);

    create index IDX_STCONFMSGFLD_DELETED on TB_SDM_STCONFIGMSGFLDS (ISDELETED);

    create index IDX_STCONFMSGFLD_FKSTFILDCONF on TB_SDM_STCONFIGMSGFLDS (FKSTFIELDCONFIG);

    alter table TB_SDM_STCONFIGMSGFLDS 
        add constraint FK_STCONFFLDMT_ASSETTYPE 
        foreign key (FKASSETTYPE) 
        references TB_SDM_ASSETTYPES;

    alter table TB_SDM_STCONFIGMSGFLDS 
        add constraint FK_STCONFFLDMSG_ENT 
        foreign key (FKENTERPRISE) 
        references TB_SDM_ENTERPRISE;

    alter table TB_SDM_STCONFIGMSGFLDS 
        add constraint FK_STCONFFLDPRV_PROV 
        foreign key (FKPROVIDER) 
        references TB_SDM_PROVIDERS;

    alter table TB_SDM_STCONFIGMSGFLDS 
        add constraint FK_STCONFFLDPRV_STCONFFLD 
        foreign key (FKSTFIELDCONFIG) 
        references TB_SDM_STCONFIGFIELDS;

    create index IX_STCONFIG_PROVIDER5 on TB_SDM_STCONFIGS (FKPROVIDER5);

    create index IX_STCONFIG_PROVIDER4 on TB_SDM_STCONFIGS (FKPROVIDER4);

    create index IX_STCONFIG_PROVIDER3 on TB_SDM_STCONFIGS (FKPROVIDER3);

    create index IX_STCONFIG_PROVIDER2 on TB_SDM_STCONFIGS (FKPROVIDER2);

    create index IX_STCONFIG_PROVIDER1 on TB_SDM_STCONFIGS (FKPROVIDER1);

    create index IDX_STCONFIG_DELETED on TB_SDM_STCONFIGS (ISDELETED);

    alter table TB_SDM_STCONFIGS 
        add constraint FK_STCONFIG_ASSETTYPE 
        foreign key (FKASSETTYPE) 
        references TB_SDM_ASSETTYPES;

    alter table TB_SDM_STCONFIGS 
        add constraint FK_STCONFIG_PROVIDER1 
        foreign key (FKPROVIDER1) 
        references TB_SDM_PROVIDERS;

    alter table TB_SDM_STCONFIGS 
        add constraint FK_STTCONFIG_PROVIDER3 
        foreign key (FKPROVIDER3) 
        references TB_SDM_PROVIDERS;

    alter table TB_SDM_STCONFIGS 
        add constraint FK_STTCONFIG_PROVIDER2 
        foreign key (FKPROVIDER2) 
        references TB_SDM_PROVIDERS;

    alter table TB_SDM_STCONFIGS 
        add constraint FK_STTCONFIG_PROVIDER5 
        foreign key (FKPROVIDER5) 
        references TB_SDM_PROVIDERS;

    alter table TB_SDM_STCONFIGS 
        add constraint FK_STTCONFIG_PROVIDER4 
        foreign key (FKPROVIDER4) 
        references TB_SDM_PROVIDERS;

    create index IX_STMRCONFIGS_STCONFIG on TB_SDM_STMRCONFIGS (FKSTCONFIG);

    create index IX_STMRCONFIGS_ASSTYPEDET on TB_SDM_STMRCONFIGS (FKASSETTYPEDETAIL);

    alter table TB_SDM_STMRCONFIGS 
        add constraint FK_STMRCONFIGS_ASSTYPEDET 
        foreign key (FKASSETTYPEDETAIL) 
        references TB_SDM_ASSETTYPE_DETAILS;

    alter table TB_SDM_STMRCONFIGS 
        add constraint FK_STMRCONFIGS_STCONFIG 
        foreign key (FKSTCONFIG) 
        references TB_SDM_STCONFIGS;

    alter table TB_SDM_VALUES 
        add constraint FK_VALUE_JOBFIELD 
        foreign key (FKJOBFIELD) 
        references TB_SDM_FIELDS;

    alter table TB_SDM_VALUES 
        add constraint FK8C4951167F57C76C 
        foreign key (FKROW) 
        references TB_SDM_ROWS;

    alter table TB_SECURITY_CONSTRAINT 
        add constraint FK_GROUP_RES_COLL 
        foreign key (COLLECTION_NAME) 
        references TB_RESOURCE_COLLECTION;

    alter table TB_SECURITY_CONSTRAINT 
        add constraint FK_RES_COLL_GROUP 
        foreign key (GROUP_ID) 
        references TB_GROUPS;

    create index IDX_CUSTODIAN_DELETED on TB_SP_CUSTODIAN_ACCOUNTS (ISDELETED);

    alter table TB_SP_CUSTODIAN_ACCOUNTS 
        add constraint FK_PROVIDER 
        foreign key (FKPROVIDER) 
        references TB_CA_EVENTPROVIDERS;

    create index IDX_CUSTOMER_DELETED on TB_SP_CUSTOMERS (ISDELETED);

    create index IDX_CUSTOMER_FKMANAGERGROUP on TB_SP_CUSTOMERS (FKCUSTMANAGERGROUP);

    alter table TB_SP_CUSTOMERS 
        add constraint FK_CUSTOMER_MANAGER 
        foreign key (FKCUSTMANAGER) 
        references TB_SP_MANAGERS;

    alter table TB_SP_CUSTOMERS 
        add constraint FK_CUSTOMER_MANAGERGROUP 
        foreign key (FKCUSTMANAGERGROUP) 
        references TB_SP_MANAGERGROUPS;

    create index IDX_MANAGERGROUP_DELETED on TB_SP_MANAGERGROUPS (ISDELETED);

    create index IDX_MANAGER_FKMANAGERGROUP on TB_SP_MANAGERS (FKMANAGERGROUP);

    create index IDX_MANAGER_DELETED on TB_SP_MANAGERS (ISDELETED);

    alter table TB_SP_MANAGERS 
        add constraint FK_MANAGER_MANAGERGROUP 
        foreign key (FKMANAGERGROUP) 
        references TB_SP_MANAGERGROUPS;

    create index IDX_MARKET_MIC on TB_SP_MARKETS (MIC);

    create index IDX_MARKET_COUNTRY on TB_SP_MARKETS (COUNTRY);

    create index IDX_MARKET_DELETED on TB_SP_MARKETS (ISDELETED);

    create index IDX_PROCESS on TB_SP_PLANNING_PROCESS (FKPROCESS);

    alter table TB_SP_PLANNING_PROCESS 
        add constraint FK_PROCESS 
        foreign key (FKPROCESS) 
        references TB_SP_PROCESS;

    create index IDX_SECURITY_FKMARKET on TB_SP_SECURITIES (FKMARKET);

    create index IDX_SECURITY_DELETED on TB_SP_SECURITIES (ISDELETED);

    create index IDX_SECURITY_TICKER on TB_SP_SECURITIES (TICKER);

    create index IDX_SECURITY_ISIN on TB_SP_SECURITIES (ISIN);

    create index IDX_SECURITY_FINANCIALASSET on TB_SP_SECURITIES (FKFINANCIALASSET);

    create index IDX_SECURITY_DETAIL on TB_SP_SECURITIES (FKDETAIL);

    create index IDX_SECURITY_CUSIP on TB_SP_SECURITIES (CUSIP);

    create index IDX_SECURITY_SEDOL on TB_SP_SECURITIES (SEDOL);

    create index IDX_SECURITY_SECURITYCLASS on TB_SP_SECURITIES (SECURITYCLASS);

    create index IDX_SECURITY_INFPROV2 on TB_SP_SECURITIES (PROVIDER_ID2);

    create index IDX_SECURITY_INFPROV1 on TB_SP_SECURITIES (PROVIDER_ID1);

    create index IDX_SECURITY_PLANIFICATION on TB_SP_SECURITIES (FKPLANIFICATION);

    create index IDX_SECURITY_INFPROV3 on TB_SP_SECURITIES (PROVIDER_ID3);

    alter table TB_SP_SECURITIES 
        add constraint FK_SECURITY_EQUITY 
        foreign key (FKSECURITYEQUITY) 
        references TB_SP_SECURITY_EQUITY;

    alter table TB_SP_SECURITIES 
        add constraint FK_FINACIAL_ASSET 
        foreign key (FKFINANCIALASSET) 
        references TB_SP_SECURITY_FASSETS;

    alter table TB_SP_SECURITIES 
        add constraint FK_SECURITY_DETAIL 
        foreign key (FKDETAIL) 
        references TB_SP_SECURITYDETAILS;

    alter table TB_SP_SECURITIES 
        add constraint FK_SECURITY_ETF 
        foreign key (FKSECURITYETF) 
        references TB_SP_SECURITY_ETF;

    alter table TB_SP_SECURITIES 
        add constraint FK_SECURITY_MARKET 
        foreign key (FKMARKET) 
        references TB_SP_MARKETS;

    alter table TB_SP_SECURITIES 
        add constraint FK_SECURITY_FIXED_INCOME 
        foreign key (FKSECURITYFIXEDINCOME) 
        references TB_SP_SECURITY_FIXED;

    alter table TB_SP_SECURITIES 
        add constraint FK_SECURITY_FUND 
        foreign key (FKSECURITYFUND) 
        references TB_SP_SECURITY_FUND;

    alter table TB_SP_SECURITIES 
        add constraint FK_PLANIFICATION 
        foreign key (FKPLANIFICATION) 
        references TB_SP_PLANNING_PROCESS;

    create index IDX_SECDETAIL_DELETED on TB_SP_SECURITYDETAILS (ISDELETED);

    create index IDX_SECDETAIL_VERSION on TB_SP_SECURITYDETAILS (VERSION);

    create index IDX_SECPORT_FKSECURITY on TB_SP_SECURITYPORTFOLIO (FKSECURITY);

    create index IDX_SECPORT_FKPORTFOLIO on TB_SP_SECURITYPORTFOLIO (FKCUSTOMER);

    create index IDX_SECPORT_DELETED on TB_SP_SECURITYPORTFOLIO (ISDELETED);

    create index IDX_SECPORT_EMISSIONDATE on TB_SP_SECURITYPORTFOLIO (EMISSIONDATE);

    create index IDX_SECPORT_VERSION on TB_SP_SECURITYPORTFOLIO (VERSION);

    alter table TB_SP_SECURITYPORTFOLIO 
        add constraint FK_SECPORT_SECURITY 
        foreign key (FKSECURITY) 
        references TB_SP_SECURITIES;

    alter table TB_SP_SECURITYPORTFOLIO 
        add constraint FK_SECPORT_PROVACCOUNTS 
        foreign key (FKPROVIDERACCOUNT) 
        references TB_SP_CUSTODIAN_ACCOUNTS;

    alter table TB_SP_SECURITYPORTFOLIO 
        add constraint FK_SECPORT_CUSTOMER 
        foreign key (FKCUSTOMER) 
        references TB_SP_CUSTOMERS;

    create index IDX_SECURITYFA_NAME on TB_SP_SECURITY_FASSETS (NAME);

    create index IDX_SECURITYFADET_FA on TB_SP_SECURITY_FASSETS_DETAILS (FKSECURITYFA);

    create index IDX_SECURITYFADET_NAME on TB_SP_SECURITY_FASSETS_DETAILS (NAME);

    alter table TB_SP_SECURITY_FASSETS_DETAILS 
        add constraint FK_DETAIL_SECFA 
        foreign key (FKSECURITYFA) 
        references TB_SP_SECURITY_FASSETS;

    alter table TB_SP_SECURITY_PLANNING 
        add constraint FK_SECURITY_PLANNING 
        foreign key (FKSECURITY) 
        references TB_SP_SECURITIES;

    alter table TB_SP_SECURITY_PLANNING 
        add constraint FK_PLANNING 
        foreign key (FKPLANNING) 
        references TB_SP_PLANNING_PROCESS;

    create index IDX_STATE_CODE on TB_STATES (CODE);

    create index IDX_STATE_FKFLOW on TB_STATES (FKFLOW);

    alter table TB_STATES 
        add constraint FK_STATE_FLOW 
        foreign key (FKFLOW) 
        references TB_FLOWS;

    create index IDX_TRANSITION_CODE on TB_TRANSITIONS (CODE);

    create index IDX_TRANSITION_FKFLOW on TB_TRANSITIONS (FKFLOW);

    alter table TB_TRANSITIONS 
        add constraint FK_TRANSITION_ENDSTATE 
        foreign key (FKENDSTATE, FKENDSTATEFLOW) 
        references TB_STATES;

    alter table TB_TRANSITIONS 
        add constraint FK_TRANSITION_INITSTATE 
        foreign key (FKINITIALSTATE, FKINITIALSTATEFLOW) 
        references TB_STATES;

    alter table TB_TRANSITIONS 
        add constraint FK_TRANSITION_FLOW 
        foreign key (FKFLOW) 
        references TB_FLOWS;

    alter table TB_USER_GROUP 
        add constraint FK_USER_GROUP 
        foreign key (USER_ID) 
        references TB_USER_PROFILES;

    alter table TB_USER_PROFILES 
        add constraint FK_USER_TYPE 
        foreign key (USER_TYPE) 
        references TB_USER_TYPE;

    create sequence ASSET_GEN;

    create sequence CAALERTS_GEN;

    create sequence CAEVDETEXT_GEN;

    create sequence CAEVENTCONF_GEN;

    create sequence CAEVENTDETAIL_GEN;

    create sequence CAEVENTHOLDINGANSWER_GEN;

    create sequence CAEVENTMESS_GEN;

    create sequence CAEVENTMRCONF_GEN;

    create sequence CAEVENT_GEN;

    create sequence CAEVFIELDCONF_GEN;

    create sequence CAEVFLDMESSCONF_GEN;

    create sequence CAEVMESSFLD_GEN;

    create sequence CAEVTYPEDET_GEN;

    create sequence CAFORMATPROV_GEN;

    create sequence CAQUESTION_GEN;

    create sequence CASPSECURITYDETAIL_GEN;

    create sequence CASPSECURITYEQUITY_GEN;

    create sequence CASPSECURITYETF_GEN;

    create sequence CASPSECURITYFIXED_GEN;

    create sequence CASPSECURITYFUND_GEN;

    create sequence NORMALIZATION_GEN;

    create sequence NORM_FILTER_GEN;

    create sequence PROCESSPROPERTY_GEN;

    create sequence PROCESS_GEN;

    create sequence SDMENTERPRISE_GEN;

    create sequence SDMERRORVALUE_GEN;

    create sequence SDMFIELDS_GEN;

    create sequence SDMJOBS_GEN;

    create sequence SDMJOBTYPESFIELDS_GEN;

    create sequence SDMJOBTYPES_GEN;

    create sequence SDMROW_GEN;

    create sequence SDMSTATUS_GEN;

    create sequence SDMVALUES_GEN;

    create sequence SECEVENTDETAIL_GEN;

    create sequence SECEVENT_GEN;

    create sequence SEQ_ASSETDETAIL_GEN;

    create sequence SEQ_ASSETDYNAMICDETAIL_GEN;

    create sequence SEQ_ASSETDYNAMIC_GEN;

    create sequence SEQ_ASSETMESSAGE_GEN;

    create sequence SEQ_ASSETSTATICDETAIL_GEN;

    create sequence SEQ_ASSETSTATIC_GEN;

    create sequence SEQ_ASSETTYPE_DETAIL_BLK_GEN;

    create sequence SEQ_ASSETTYPE_DETAIL_GEN;

    create sequence SEQ_CONFIGFIELDS_GEN;

    create sequence SEQ_STCONFIGMSGFLDS_GEN;

    create sequence SEQ_STCONF_GEN;

    create sequence SEQ_STMRCONF_GEN;

    create sequence SEQ_TB_SDM_PROVIDER_GEN;

    create sequence SPCUSTOMER_GEN;

    create sequence SPMANAGERGROUP_GEN;

    create sequence SPMANAGER_GEN;

    create sequence SPMARKET_GEN;

    create sequence SPMJOBTYPES_GEN;

    create sequence SPPERSECURITYPLANIFICATION_GEN;

    create sequence SPPLANIFICATION_GEN;

    create sequence SPPROCESS_GEN;

    create sequence SPPROVIDERACCOUNT_GEN;

    create sequence SPSECFADET_GEN;

    create sequence SPSECPORT_GEN;

    create sequence SPSECURITY_GEN;

    create sequence TASKEXECUTION_GEN;

    create sequence TASK_GEN;

    create sequence TBLOGFIELD_GEN;

    create sequence TBLOG_GEN;

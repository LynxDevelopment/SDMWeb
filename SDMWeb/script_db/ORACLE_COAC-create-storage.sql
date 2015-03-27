    create table @database.schema@.HS_CA_EVENTDETAILS (
        ID number(19,0) not null,
        VERSION number(10,0) not null,
        REFID number(19,0),
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        ISDELETED number(1,0) not null,
        EXPIRATIONDATE date,
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
        SUSCRIPTIONDATE date,
        EXECUTIONDATE date,
        OPERATIONALDATE date,
        primary key (ID)
     )
	 TABLESPACE "@tables.tablespace@"
	 STORAGE ( 
		 INITIAL 400M 
		 NEXT 100M 
		 MAXEXTENTS UNLIMITED 
		 PCTINCREASE 10
	 );

    create table @database.schema@.HS_CA_EVENTEXTDETAILS (
        ID number(19,0) not null,
        VERSION number(10,0) not null,
        REFID number(19,0),
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        ISDELETED number(1,0) not null,
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
        EXTENSIONIDCODE varchar2(255 char),
        EXTENSIONNAME varchar2(16 char) not null,
        EXTENSIONNUMBER number(10,0) not null,
        NARRATIVE clob,
        FKEVENTDETAIL number(19,0) not null,
        primary key (ID),
        unique (FKEVENTDETAIL, EXTENSIONNAME, EXTENSIONNUMBER, VERSION)
    )
	TABLESPACE "@tables.tablespace@"
	STORAGE ( 
		INITIAL 400M 
        NEXT 100M 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create table @database.schema@.HS_CA_EVENTMESSAGEFIELDS (
        ID number(19,0) not null,
        FIELDTYPE number(10,0) not null,
        PATH varchar2(128 char) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        VALUESHORT varchar2(16 char),
        VALUELONG varchar2(128 char),
        VALUEVERYLONG varchar2(256 char),
        valueClob clob,
        FKHSEVENTMESSAGE number(19,0) not null,
        primary key (ID)
    )
    TABLESPACE "@tables.tablespace@"
	STORAGE ( 
		INITIAL 946M 
        NEXT 100M 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create table @database.schema@.HS_CA_EVENTMESSAGES (
        ID number(19,0) not null,
        VERSION number(10,0) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        UPDDATE timestamp not null,
        ISDELETED number(1,0) not null,
        SECURITYTYPE varchar2(64 char),
        ISINPORTFOLIO number(1,0),
        SECURITY varchar2(64 char),
        ISEXTENSION number(1,0),
        EVENTTYPE varchar2(64 char),
        TRANSMESSKEY varchar2(128 char),
        ISENDED number(1,0) not null,
        ISTRANSMESSERROR number(1,0) not null,
        TRANSMESSPARM varchar2(256 char),
        FKEVENT number(19,0),
        ORIGINID varchar2(16 char),
        ORIGINNAME varchar2(512 char),
        ORIGINTYPE varchar2(64 char),
        ORIGINPOSITION number(10,0),
        MESSAGEID varchar2(64 char),
        OPERATION varchar2(64 char),
		ISMAINMARKET number(1,0),
        SENDER varchar2(64 char),
        EXTENSIONREF varchar2(64 char),
        RECEIVER varchar2(64 char),
        EVENTREFERENCE varchar2(64 char),
        EVENTMESSGREF varchar2(64 char),
        PREVEVENTMESSGREF varchar2(64 char),
        SECURITYNAME varchar2(128 char),
        ANNOUNCEDATE date,
        EFFECTIVEDATE date,
        SENDERTIMESTAMP timestamp,
        ACCOUNT varchar2(64 char),
        AMOUNT number(19,0),
        ORIGINALMESSAGE clob,
        OPERATIONNORM varchar2(6 char),
        FKTYPE varchar2(16 char),
        FKFORMAT varchar2(16 char),
        FKSECURITY number(19,0),
        FKSTATE varchar2(16 char) not null,
        FKSTATEFLOW varchar2(16 char) not null,
        FKEVENTTYPE varchar2(4 char),
        FKPROVIDER varchar2(16 char),
        primary key (ID)
    )
    TABLESPACE "@tables.tablespace@"
	STORAGE ( 
		INITIAL 394M 
        NEXT 40M 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create table @database.schema@.HS_CA_EVENTS (
        EVENTCLASS varchar2(32 char) not null,
        ID number(19,0) not null,
        VERSION number(10,0) not null,
        REFID number(19,0),
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        ISDELETED number(1,0) not null,
        EXPIRATIONDATE date,
        SUBSCRIPTIONDATE date,
        EXECUTIONDATE date,
        OPERATIONALDATE date,
        ISPROVUPDATED number(1,0) not null,
        ISPROVCANCELLED number(1,0) not null,
        TRANSMESSKEY varchar2(128 char),
        ISENDED number(1,0) not null,
        ISTRANSMESSERROR number(1,0) not null,
        TRANSMESSPARM varchar2(256 char),
        ISCOMPLETE number(1,0),
        FKEVENTPROVIDER varchar2(16 char),
        FKSTATE varchar2(16 char) not null,
        FKSTATEFLOW varchar2(16 char) not null,
        FKEVENTDETAIL number(19,0),
        FKEVENTTYPE varchar2(4 char) not null,
        FKEVENTGROUP number(19,0),
        FKMASTEREVENT number(19,0),
        FKSECURITY number(19,0),
        primary key (ID)
    )
	TABLESPACE "@tables.tablespace@";

    create table @database.schema@.HS_SP_SECURITYPORTFOLIO (
        ID number(19,0) not null,
        VERSION number(10,0) not null,
        REFID number(19,0),
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        ISDELETED number(1,0) not null,
		EMISSIONDATE date not null,
        CUSTOMERAMOUNT number(19,0),
        CUSTODIANAMOUNT number(19,0),
        FKCUSTOMER number(19,0) not null,
        FKSECURITY number(19,0) not null,
        primary key (ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_ANSWERS (
        POLL_ID number(10,0) not null,
        ANSWER_TEXT varchar2(200 char),
        HIT number(19,0),
        INITIAL_HIT number(19,0),
        ANSWERS_ID number(10,0) not null,
        primary key (POLL_ID, ANSWERS_ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_APPLICATIONS (
        ID varchar2(16 char) not null,
        SOFTWAREVERSION number(10,6),
        SOFTWAREDATE date,
        DATABASEVERSION number(10,6),
        DATABASEDATE date,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        UPDDATE timestamp not null,
        ISDELETED number(1,0) not null,
        NAME varchar2(64 char) not null,
        version number(10,0) not null,
        DESCRIPTION varchar2(128 char),
        primary key (ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CATEGORIES (
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
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CATEGORY_PERMISSION (
        CATEGORY_ID number(10,0) not null,
        PRINCIPAL varchar2(50 char),
        PRINCIPAL_TYPE varchar2(20 char),
        READ_PERMISSION number(1,0),
        WRITE_PERMISSION number(1,0)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CA_ALERTS (
        ID number(19,0) not null,
        QUERY varchar2(256 char) not null,
        VERSION number(10,0) not null,
        DESCRIPTION varchar2(256 char) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        UPDDATE timestamp not null,
        ISDELETED number(1,0) not null,
        LABEL varchar2(256 char) not null,
        GRAPH varchar2(1 char) not null,
        USER_ROLE varchar2(2 char) not null,
        LINK varchar2(128 char),
        primary key (ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CA_DOMAINCLUSTERFORMATS (
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        FKMESSAGEFORMAT varchar2(16 char),
        FKAPPLICATION varchar2(16 char),
        FKDOMAIN varchar2(16 char),
        FKDOMAINCLUSTER varchar2(32 char),
        primary key (FKMESSAGEFORMAT, FKAPPLICATION, FKDOMAIN, FKDOMAINCLUSTER)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CA_EVENTCONFIGFIELDS (
        ID number(19,0) not null,
        VERSION number(10,0) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        UPDDATE timestamp not null,
        ISDELETED number(1,0) not null,
        ISREQUIRED number(1,0) not null,
        FKEVENTCONFIG number(19,0) not null,
        FKEVENTTYPEDETAIL number(19,0) not null,
        primary key (ID),
        unique (FKEVENTCONFIG, FKEVENTTYPEDETAIL)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CA_EVENTCONFIGMSGFLDS (
        ID number(19,0) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        UPDDATE timestamp not null,
        ISDELETED number(1,0) not null,
        NORMSCRIPT varchar2(2048 char),
        VERSION number(10,0) not null,
        FKMSGTYPE varchar2(16 char) not null,
        FKFORMAT varchar2(16 char) not null,
        FKEVENTFIELDCONFIG number(19,0) not null,
        FKEVENTPROVIDER varchar2(16 char),
        primary key (ID),
        unique (FKEVENTFIELDCONFIG, FKFORMAT, FKMSGTYPE, FKEVENTPROVIDER)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CA_EVENTCONFIGS (
        ID number(19,0) not null,
        VERSION number(10,0) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        UPDDATE timestamp not null,
        ISDELETED number(1,0) not null,
        PRIMSECNOTFOUND varchar2(32 char) not null,
        CUSTMANNOTFOUND varchar2(32 char) not null,
        FKSECPREFPROV2 varchar2(16 char),
        FKEVENTTYPE varchar2(4 char) not null,
        FKPREFPROVIDER1 varchar2(16 char) not null,
        primary key (ID),
        unique (FKEVENTTYPE)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CA_EVENTDETAILS (
        ID number(19,0) not null,
        VERSION number(10,0) not null,
        REFID number(19,0),
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        ISDELETED number(1,0) not null,
        EXPIRATIONDATE date,
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
        SUSCRIPTIONDATE date,
        EXECUTIONDATE date,
        OPERATIONALDATE date,
        primary key (ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CA_EVENTEXTDETAILS (
        ID number(19,0) not null,
        VERSION number(10,0) not null,
        REFID number(19,0),
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        ISDELETED number(1,0) not null,
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
        EXTENSIONIDCODE varchar2(255 char),
        EXTENSIONNAME varchar2(16 char) not null,
        EXTENSIONNUMBER number(10,0) not null,
        NARRATIVE clob,
        FKEVENTDETAIL number(19,0) not null,
        primary key (ID),
        unique (FKEVENTDETAIL, EXTENSIONNAME, EXTENSIONNUMBER, VERSION)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CA_EVENTMESSAGEFIELDS (
        ID number(19,0) not null,
        FIELDTYPE number(10,0) not null,
        PATH varchar2(128 char) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        VALUESHORT varchar2(16 char),
        VALUELONG varchar2(128 char),
        VALUEVERYLONG varchar2(256 char),
        valueClob clob,
        FKEVENTMESSAGE number(19,0) not null,
        primary key (ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CA_EVENTMESSAGES (
        ID number(19,0) not null,
        VERSION number(10,0) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        UPDDATE timestamp not null,
        ISDELETED number(1,0) not null,
        SECURITYTYPE varchar2(64 char),
        ISINPORTFOLIO number(1,0),
        SECURITY varchar2(64 char),
        ISEXTENSION number(1,0),
        EVENTTYPE varchar2(64 char),
        TRANSMESSKEY varchar2(128 char),
        ISENDED number(1,0) not null,
        ISTRANSMESSERROR number(1,0) not null,
        TRANSMESSPARM varchar2(256 char),
        FKEVENT number(19,0),
        ORIGINID varchar2(16 char),
        ORIGINNAME varchar2(512 char),
        ORIGINTYPE varchar2(64 char),
        ORIGINPOSITION number(10,0),
        MESSAGEID varchar2(64 char),
        OPERATION varchar2(64 char),
        SENDER varchar2(64 char),
        EXTENSIONREF varchar2(64 char),
        RECEIVER varchar2(64 char),
        EVENTREFERENCE varchar2(64 char),
        EVENTMESSGREF varchar2(64 char),
        PREVEVENTMESSGREF varchar2(64 char),
        SECURITYNAME varchar2(128 char),
        ANNOUNCEDATE date,
        EFFECTIVEDATE date,
        SENDERTIMESTAMP timestamp,
        ACCOUNT varchar2(64 char),
        AMOUNT number(19,0),
        ORIGINALMESSAGE clob,
        OPERATIONNORM varchar2(6 char),
		ISMAINMARKET number(1,0),
        FKEVENTTYPE varchar2(4 char),
        FKPROVIDER varchar2(16 char),
        FKSTATE varchar2(16 char) not null,
        FKSTATEFLOW varchar2(16 char) not null,
        FKTYPE varchar2(16 char),
        FKFORMAT varchar2(16 char),
        FKSECURITY number(19,0),
        primary key (ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CA_EVENTMRCONFIGS (
        ID number(19,0) not null,
        TARGET varchar2(64 char),
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        RULEORDER number(10,0),
        CONDITION varchar2(32 char),
        ONTRUERESULT varchar2(32 char),
        ONFALSERESULT varchar2(32 char),
        CONDITIONPARAMS varchar2(128 char),
        FKEVENTTYPEDETAIL number(19,0) not null,
        FKEVENTCONFIG number(19,0) not null,
        primary key (ID),
        unique (FKEVENTCONFIG, RULEORDER)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CA_EVENTPROVIDERS (
        EVENTPROVCLASS varchar2(16 char) not null,
        ID varchar2(16 char) not null,
        NAME varchar2(64 char) not null,
        VERSION number(10,0) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        UPDDATE timestamp not null,
        ISDELETED number(1,0) not null,
        primary key (ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CA_EVENTS (
        EVENTCLASS varchar2(32 char) not null,
        ID number(19,0) not null,
        VERSION number(10,0) not null,
        REFID number(19,0),
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        ISDELETED number(1,0) not null,
        EXPIRATIONDATE date,
        SUBSCRIPTIONDATE date,
        EXECUTIONDATE date,
        OPERATIONALDATE date,
        ISPROVUPDATED number(1,0) not null,
        ISPROVCANCELLED number(1,0) not null,
		ISMANDATORY number(1,0) not null,
        TRANSMESSKEY varchar2(128 char),
        ISENDED number(1,0) not null,
        ISTRANSMESSERROR number(1,0) not null,
        TRANSMESSPARM varchar2(256 char),
        ISCOMPLETE number(1,0),
		ENTITYOPERATIONALDATE date,
        FKEVENTDETAIL number(19,0),
        FKMASTEREVENT number(19,0),
        FKEVENTGROUP number(19,0),
        FKSECURITY number(19,0),
        FKEVENTPROVIDER varchar2(16 char),
        FKEVENTTYPE varchar2(4 char) not null,
        FKSTATE varchar2(16 char) not null,
        FKSTATEFLOW varchar2(16 char) not null,
        primary key (ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CA_EVENTTYPEDETAILS (
        ID number(19,0) not null,
        MAXLENGTH number(10,0) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        UPDDATE timestamp not null,
        ISDELETED number(1,0) not null,
        FIELDPATH varchar2(32 char) not null,
        ISDISPLAYINTABLE number(1,0) not null,
        DISPLAYINTABLEORDER number(10,0) not null,
        DISPLAYGROUP varchar2(64 char) not null,
        DISPLAYGROUPORDER number(10,0) not null,
        DISPLAYINGROUPORDER number(10,0),
        DISPLAYROW number(10,0),
        DISPLAYCOLUMN number(10,0),
        DISPLAYTOP number(10,0),
        DISPLAYLEFT number(10,0),
        ISEXTENSION number(1,0) not null,
        ISBASIC number(1,0) not null,
        EDITABLENORMALIZATION number(1,0) not null,
        NAME varchar2(64 char) not null,
        ISHIDDEN number(1,0) not null,
        VERSION number(10,0) not null,
        DESCRIPTION varchar2(256 char),
        FORMAT varchar2(32 char),
        FIELDTYPE varchar2(16 char) not null,
        FKEVENTTYPE varchar2(4 char) not null,
        primary key (ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CA_EVENTTYPES (
        ID varchar2(4 char) not null,
        NAME varchar2(64 char) not null,
        DESCRIPTION varchar2(256 char),
        ISOPTIONAL number(1,0) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        primary key (ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CA_FORMATPROVIDERS (
        ID number(19,0) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        IDATMESSAGE varchar2(16 char) not null,
        FKMESSAGEFORMAT varchar2(16 char) not null,
        FKEVENTPROVIDER varchar2(16 char) not null,
        primary key (ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CA_MESSAGEFIELDCONFIGS (
        PATH varchar2(128 char) not null,
        HIDDEN number(1,0) not null,
        VERSION number(10,0) not null,
        DESCRIPTION varchar2(128 char),
        FIELDNAME varchar2(64 char) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        UPDDATE timestamp not null,
        ISDELETED number(1,0) not null,
        FIELDTYPE varchar2(32 char) not null,
        DISPLAYGROUP varchar2(64 char),
        DISPLAYROW number(10,0) not null,
        DISPLAYCOLUMN number(10,0) not null,
        FIELDLENGTH number(10,0),
        FIELDFORMAT varchar2(32 char),
        FKTYPE varchar2(16 char) not null,
        FKFORMAT varchar2(16 char) not null,
        primary key (FKTYPE, FKFORMAT, PATH)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CA_MESSAGEFORMATS (
        ID varchar2(16 char) not null,
        NAME varchar2(64 char) not null,
        PATH varchar2(128 char) not null,
        VERSION number(10,0) not null,
        PATTERN varchar2(64 char) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        UPDDATE timestamp not null,
        ISDELETED number(1,0) not null,
        primary key (ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CA_MESSAGETYPES (
        CODE varchar2(16 char) not null,
        NAME varchar2(64 char) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        FKFORMAT varchar2(16 char) not null,
        primary key (CODE, FKFORMAT)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CONFIGS (
        ID varchar2(32 char) not null,
        ISUPDATABLE number(1,0) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        UPDDATE timestamp not null,
        ISDELETED number(1,0) not null,
        BOOLEANVALUE number(1,0),
        LONGVALUE number(19,0),
        DOUBLEVALUE double precision,
        TIMESTAMPVALUE timestamp,
        SHORTSTRINGVALUE varchar2(16 char),
        MIDDLESTRINGVALUE varchar2(64 char),
        LONGSTRINGVALUE varchar2(128 char),
        VERYLONGSTRINGVALUE varchar2(256 char),
        TYPEVALUE varchar2(15 char) not null,
        VERSION number(10,0) not null,
        DESCRIPTION varchar2(128 char),
        ISEDITABLE number(1,0) not null,
        FKAPPLICATION varchar2(16 char) not null,
        primary key (FKAPPLICATION, ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CONTAINER (
        NAME varchar2(100 char) not null,
        TYPE varchar2(15 char) not null,
        primary key (NAME, TYPE)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CONTAINER_SCHEDULE (
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
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CONTENT (
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
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CONTENTS_CATEGORIES (
        CONTENT_ID number(10,0) not null,
        CATEGORY_ID number(10,0) not null,
        primary key (CATEGORY_ID, CONTENT_ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CONTENT_PERMISSION (
        CONTENT_ID number(10,0) not null,
        PRINCIPAL varchar2(50 char),
        PRINCIPAL_TYPE varchar2(20 char),
        READ_PERMISSION number(1,0),
        WRITE_PERMISSION number(1,0)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CONTENT_STATUS (
        STATUS_ID number(10,0) not null,
        DESCRIPTION varchar2(20 char),
        primary key (STATUS_ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_DIARY (
        CONTENT_ID number(10,0) not null,
        SUBJECT varchar2(100 char),
        MESSAGE_DATE timestamp,
        MESSAGE clob,
        primary key (CONTENT_ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_DOCUMENTS (
        CONTENT_ID number(10,0) not null,
        FILE_NAME varchar2(100 char),
        SUBJECT varchar2(100 char),
        DESCRIPTION varchar2(1000 char),
        KEYWORDS varchar2(2000 char),
        primary key (CONTENT_ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_DOCUMENT_VERSIONS (
        CONTENT_ID number(10,0) not null,
        AUTHOR varchar2(100 char),
        VERSION_DATE timestamp,
        SAVED_FILE_NAME varchar2(256 char),
        MIME_TYPE varchar2(50 char),
        DOCUMENT_ID number(10,0),
        ATTACHED_TO number(10,0),
        VERSION_NUMBER number(10,0),
        primary key (CONTENT_ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_DOMAINCLUSTERS (
        CODE varchar2(32 char) not null,
        NAME varchar2(64 char) not null,
        VERSION number(10,0) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        UPDDATE timestamp not null,
        ISDELETED number(1,0) not null,
        FKAPPLICATION varchar2(16 char),
        FKDOMAIN varchar2(16 char),
        primary key (FKAPPLICATION, FKDOMAIN, CODE)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_DOMAINNORMS (
        CODE varchar2(32 char) not null,
        VERSION number(10,0) not null,
        DESCRIPTION varchar2(64 char),
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        UPDDATE timestamp not null,
        ISDELETED number(1,0) not null,
        FKAPPLICATION varchar2(16 char),
        FKDOMAIN varchar2(16 char),
        primary key (FKAPPLICATION, FKDOMAIN, CODE)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_DOMAINS (
        CODE varchar2(16 char) not null,
        ISUPDATABLE number(1,0) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        UPDDATE timestamp not null,
        ISDELETED number(1,0) not null,
        ISDELETABLE number(1,0) not null,
        ISCACHEABLE number(1,0) not null,
        NAME varchar2(64 char),
        VERSION number(10,0) not null,
        FKAPPLICATION varchar2(16 char) not null,
        primary key (FKAPPLICATION, CODE)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_DOMAINVALUES (
        ORIGINALVALUE varchar2(64 char) not null,
        VERSION number(10,0) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        UPDDATE timestamp not null,
        ISDELETED number(1,0) not null,
        FKCLTAPPLICATION varchar2(16 char),
        FKCLTDOMAIN varchar2(16 char),
        FKCLUSTER varchar2(32 char),
        FKNRMAPPLICATION varchar2(16 char),
        FKNRMDOMAIN varchar2(16 char),
        FKNORM varchar2(32 char),
        primary key (ORIGINALVALUE, FKNRMAPPLICATION, FKNRMDOMAIN, FKNORM, FKCLTAPPLICATION, FKCLTDOMAIN, FKCLUSTER)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_FLOWS (
        ID varchar2(16 char) not null,
        NAME varchar2(64 char) not null,
        DESCRIPTION varchar2(128 char),
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        FKAPPLICATION varchar2(16 char) not null,
        primary key (ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_FUNCTIONS (
        FUNCTION_ID varchar2(30 char) not null,
        MODULE_ID varchar2(30 char) not null,
        FUNCTION_URI varchar2(255 char),
        FUNCTION_TITLE varchar2(100 char),
        primary key (FUNCTION_ID, MODULE_ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_FUNCTION_GROUPS (
        GROUP_ID varchar2(30 char) not null,
        FUNCTION_ID varchar2(30 char) not null,
        MODULE_ID varchar2(30 char) not null,
        primary key (GROUP_ID, FUNCTION_ID, MODULE_ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_GROUPS (
        GROUP_ID varchar2(30 char) not null,
        GROUP_TYPE number(10,0) not null,
        DESCRIPTION varchar2(255 char),
        GROUP_RULE varchar2(255 char),
        primary key (GROUP_ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_GROUP_TYPES (
        GROUP_TYPE number(10,0) not null,
        DESCRIPTION varchar2(255 char),
        ADMIN_PAGE_URL varchar2(255 char),
        primary key (GROUP_TYPE)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_HTML (
        CONTENT_ID number(10,0) not null,
        TITLE varchar2(100 char),
        BODY clob,
        primary key (CONTENT_ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_LOGFIELDUPDATES (
        ID number(10,0) not null,
        FIELDNAME varchar2(128 char) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        OLDVALUE varchar2(256 char) not null,
        NEWVALUE varchar2(256 char) not null,
        FKLOG number(19,0) not null,
        primary key (ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_LOGS (
        LOGCLASS varchar2(32 char) not null,
        ID number(19,0) not null,
        MESSAGE varchar2(1750 char) not null,
        LOGTYPE varchar2(128 char) not null,
        LOGLEVEL number(10,0) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        FKAPPLICATION varchar2(16 char) not null,
        STACKTRACE clob,
        ENTITYNAME varchar2(255 char),
        OPERATIONID number(19,0),
        FKFLOW varchar2(16 char),
        FKTRANSITION varchar2(16 char),
        UPDATEDENTITY varchar2(128 char),
        primary key (ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_MENU_ITEMS (
        ITEM_TYPE varchar2(31 char) not null,
        MENU_ITEM_ID varchar2(255 char) not null,
        URL varchar2(255 char),
        MENU_ITEM_TITLE varchar2(255 char),
        LANGUAGE_ID varchar2(255 char),
        MARKED char(1 char),
        ENABLED char(1 char),
        ICON1 varchar2(255 char),
        ICON2 varchar2(255 char),
        EXTERNAL_LINK number(1,0),
        TARGET_LINK number(1,0),
        CATEGORY_ID number(10,0),
        CONTAINER_NAME varchar2(100 char),
        CONTAINER_TYPE varchar2(15 char),
        PARENT varchar2(255 char),
        CHILD_INDEX number(10,0),
        primary key (MENU_ITEM_ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_MENU_ITEM_GROUP (
        MENU_ITEM_ID varchar2(255 char) not null,
        GROUP_ID varchar2(30 char) not null,
        primary key (MENU_ITEM_ID, GROUP_ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_MESSAGE (
        CONTENT_ID number(10,0) not null,
        SUBJECT varchar2(100 char),
        MESSAGE_DATE timestamp,
        MESSAGE clob,
        primary key (CONTENT_ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_MIME_TYPES (
        MIME_TYPE varchar2(50 char) not null,
        MIME_DESCRIPTION varchar2(200 char),
        primary key (MIME_TYPE)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_MODULES (
        MODULE_ID varchar2(30 char) not null,
        ADMIN_URI varchar2(200 char),
        MODULE_TITLE varchar2(100 char),
        CONTENT_LOCATION varchar2(100 char),
        DESCRIPTION varchar2(200 char),
        ICON1 varchar2(250 char),
        ICON2 varchar2(250 char),
        MODULE_TYPE number(10,0),
        primary key (MODULE_ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_MODULE_TYPES (
        MODULE_TYPE number(10,0) not null,
        DESCRIPTION varchar2(200 char),
        primary key (MODULE_TYPE)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_NEWS (
        CONTENT_ID number(10,0) not null,
        TITLE varchar2(255 char),
        SUBTITLE varchar2(255 char),
        BODY clob,
        primary key (CONTENT_ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_NEWS_LINK (
        NEWS_ID number(10,0) not null,
        URL varchar2(255 char),
        NEWS_LINK_TITLE varchar2(255 char),
        ALT varchar2(255 char),
        LINK_ID number(10,0) not null,
        primary key (NEWS_ID, LINK_ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_NEWS_PICTURE (
        NEWS_ID number(10,0) not null,
        URL varchar2(255 char),
        ALT varchar2(255 char),
        PICTURE_NAME varchar2(255 char),
        PICTURE_ID number(10,0) not null,
        primary key (NEWS_ID, PICTURE_ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_OLD_PASSWORDS (
        USER_ID varchar2(30 char) not null,
        PASSWORD varchar2(30 char),
        PASSWORD_TIMESTAMP timestamp
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_PAGES (
        SEQUENCE_NUMBER number(19,0) not null,
        TRACKING_ID number(19,0) not null,
        ACCESS_TIME timestamp,
        PAGE_NAME varchar2(80 char),
        REFERRER varchar2(80 char),
        primary key (SEQUENCE_NUMBER, TRACKING_ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_POLL (
        CONTENT_ID number(10,0) not null,
        DESCRIPTION varchar2(100 char),
        QUESTION varchar2(200 char),
        QUESTION_TYPE number(10,0),
        primary key (CONTENT_ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_POLL_USER (
        POLL_ID number(10,0) not null,
        USER_ID varchar2(30 char)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_POPUP_SCHEDULE (
        SCHEDULE_ID number(10,0) not null,
        HEIGHT number(19,0),
        WIDTH number(19,0),
        LEFT_POS number(19,0),
        TOP_POS number(19,0),
        TIMER number(19,0),
        VIEW_TIMES number(19,0),
        primary key (SCHEDULE_ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_RESOURCE_COLLECTION (
        COLLECTION_NAME varchar2(20 char) not null,
        DESCRIPTION varchar2(200 char),
        URL_PATTERN varchar2(200 char),
        CONSTRAINTS_ENABLED number(1,0),
        primary key (COLLECTION_NAME)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_SECURITY_CONSTRAINT (
        COLLECTION_NAME varchar2(20 char) not null,
        GROUP_ID varchar2(30 char) not null,
        primary key (COLLECTION_NAME, GROUP_ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_SP_MANAGERGROUPS (
        ID number(19,0) not null,
        NAME varchar2(64 char) not null,
        VERSION number(10,0) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        UPDDATE timestamp not null,
        ISDELETED number(1,0) not null,
        CUSTOMERID varchar2(64 char),
        primary key (ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_SP_MANAGERS (
        ID number(19,0) not null,
        NAME varchar2(64 char) not null,
        VERSION number(10,0) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        UPDDATE timestamp not null,
        ISDELETED number(1,0) not null,
        CUSTOMERID varchar2(64 char),
        FKMANAGERGROUP number(19,0),
		EMAIL varchar2(64 char),
        primary key (ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_SP_MARKETS (
        ID number(19,0) not null,
        NAME varchar2(128 char),
        COUNTRY varchar2(2 char),
        VERSION number(10,0) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        UPDDATE timestamp not null,
        ISDELETED number(1,0) not null,
        CUSTOMERID varchar2(64 char),
        TICKER varchar2(32 char),
        MIC varchar2(4 char) not null,
        CITY varchar2(32 char),
        primary key (ID)
    )
    TABLESPACE "@tables.tablespace@"
	STORAGE ( 
		INITIAL 200K 
        NEXT 20K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create table @database.schema@.TB_SP_CUSTOMERS (
        MANAGERTYPE varchar2(16 char) not null,
        ID number(19,0) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        UPDDATE timestamp not null,
        ISDELETED number(1,0) not null,
        CUSTOMERID varchar2(64 char),
        NAME varchar2(64 char) not null,
        VERSION number(10,0) not null,
		FKCUSTMANAGER number(19,0),
        FKCUSTMANAGERGROUP number(19,0),
        primary key (ID)
    )
    TABLESPACE "@tables.tablespace@";

	create table @database.schema@.TB_SP_CUSTODIAN_ACCOUNTS (
        ID number(19,0) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        UPDDATE timestamp not null,
        ISDELETED number(1,0) not null,
        ACCOUNTNUMBER varchar2(64 char) not null,
        OWNACCOUNT number(1,0) not null,
        NAME varchar2(64 char) not null,
        VERSION number(10,0) not null,
        FKPROVIDER varchar2(16 char),
        primary key (ID)
    )TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_SP_SECURITIES (
        SECURITYCLASS varchar2(16 char) not null,
        ID number(19,0) not null,
        NAME varchar2(64 char) not null,
        COUNTRY varchar2(2 char),
        VERSION number(10,0) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        UPDDATE timestamp not null,
        ISDELETED number(1,0) not null,
        ISIN varchar2(12 char),
        CUSIP varchar2(9 char),
        SEDOL varchar2(7 char),
        PROVIDER_ID1 varchar2(16 char),
        PROVIDER_ID2 varchar2(16 char),
        PROVIDER_ID3 varchar2(16 char),
        SEC_TYPE varchar2(32 char),
        IND_SECTOR varchar2(32 char),
        IND_GROUP varchar2(32 char),
        IND_SUBGROUP varchar2(32 char),
        REL_INDEX varchar2(8 char),
        INCUSTPORTFOLIO number(1,0) not null,
        CURRENCY varchar2(3 char),
        CUSTOMERID varchar2(64 char),
        TICKER varchar2(32 char),
        EXPIRATIONDATE date,
        FKDETAIL number(19,0),
        FKMARKET number(19,0),
        FKFINANCIALASSET varchar2(4 char),
        FKPLANIFICATION number(19,0),
        primary key (ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_SP_SECURITYDETAILS (
        ID number(19,0) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        UPDDATE timestamp not null,
        ISDELETED number(1,0) not null,
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
    )
    TABLESPACE "@tables.tablespace@";

	create table @database.schema@.TB_SP_SECURITY_PLANNING (
        ID number(19,0) not null,
        FKPLANNING number(19,0),
        FKSECURITY number(19,0),
        primary key (ID)
    )TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_SP_SECURITYPORTFOLIO (
        ID number(19,0) not null,
        VERSION number(10,0) not null,
        REFID number(19,0),
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        ISDELETED number(1,0) not null,
        CUSTOMERAMOUNT number(19,0),
        CUSTODIANAMOUNT number(19,0),
        EMISSIONDATE date not null,
		FKCUSTOMER number(19,0) not null,
        FKPROVIDERACCOUNT number(19,0) not null,
        FKSECURITY number(19,0) not null,
        primary key (ID)
    )
    TABLESPACE "@tables.tablespace@";

	create table @database.schema@.TB_SP_SECURITY_FASSETS (
        ID varchar2(4 char) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        NAME varchar2(64 char),
        primary key (ID)
    )
	TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_SP_SECURITY_FASSETS_DETAILS (
        ID number(19,0) not null,
        NAME varchar2(64 char) not null,
        ISHIDDEN number(1,0) not null,
        VERSION number(10,0) not null,
        DESCRIPTION varchar2(256 char),
        MAXLENGTH number(10,0) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        UPDDATE timestamp not null,
        ISDELETED number(1,0) not null,
        FIELDPATH varchar2(32 char) not null,
        FIELDTYPE varchar2(16 char) not null,
        FORMAT varchar2(32 char),
        ISDISPLAYINTABLE number(1,0) not null,
        DISPLAYINTABLEORDER number(10,0) not null,
        DISPLAYGROUP varchar2(64 char) not null,
        DISPLAYGROUPORDER number(10,0) not null,
        DISPLAYINGROUPORDER number(10,0),
        DISPLAYROW number(10,0),
        DISPLAYCOLUMN number(10,0),
        DISPLAYTOP number(10,0),
        DISPLAYLEFT number(10,0),
        ISEXTENSION number(1,0) not null,
        ISSTORE number(1,0) not null,
        ISBASIC number(1,0) not null,
        FKSECURITYFA varchar2(4 char) not null,
        primary key (ID)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_STATES (
        CODE varchar2(16 char) not null,
        NAME varchar2(64 char) not null,
        DESCRIPTION varchar2(128 char),
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        ISINITIAL number(1,0) not null,
        ISEND number(1,0) not null,
        FKFLOW varchar2(16 char) not null,
        primary key (CODE, FKFLOW)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_TRACKING (
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
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_TRANSITIONS (
        CODE varchar2(16 char) not null,
        NAME varchar2(64 char) not null,
        DESCRIPTION varchar2(128 char),
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        TRANSITIONMESSAGE varchar2(128 char),
        TRANSITIONEXECLASS varchar2(128 char),
        FKFLOW varchar2(16 char) not null,
        FKINITIALSTATE varchar2(16 char),
        FKINITIALSTATEFLOW varchar2(16 char),
        FKENDSTATE varchar2(16 char) not null,
        FKENDSTATEFLOW varchar2(16 char) not null,
        primary key (CODE, FKFLOW)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_USER_GROUP (
        USER_ID varchar2(30 char) not null,
        GROUP_ID varchar2(30 char)
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_USER_PROFILES (
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
    )
    TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_USER_TYPE (
        TYPE varchar2(20 char) not null,
        CLASSNAME varchar2(100 char),
        primary key (TYPE)
    )
    TABLESPACE "@tables.tablespace@";

	create table @database.schema@.TB_SP_PROCESS (
        PROCESSTYPE varchar2(16 char) not null,
        ID number(19,0) not null,
        REFID number(19,0),
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        ISDELETED number(1,0) not null,
        VERSION number(10,0) not null,
        DESCRIPTION varchar2(16 char) not null,
        OVERWRITE number(1,0) not null,
        FILENAME varchar2(64 char) not null,
        PATH varchar2(64 char) not null,
        FILEEXTENSION varchar2(6 char) not null,
        FTP_USER varchar2(128 char),
        FTP_PASSWORD varchar2(128 char),
        FTP_SERVER varchar2(128 char),
        FTP_PATH varchar2(256 char),
        FTP_FILE varchar2(128 char),
        FTP_BINARY number(1,0),
        FTP_PASSIVE_MODE number(1,0),
        FTP_PROXY varchar2(128 char),
        FTP_PORT varchar2(6 char),
        PROXY_USER varchar2(128 char),
        PROXY_PASSWORD varchar2(128 char),
        SHELL varchar2(256 char),
        primary key (ID)
    )
	TABLESPACE "@tables.tablespace@";

	create table @database.schema@.TB_SP_PLANNING_PROCESS (
        ID number(19,0) not null,
        REFID number(19,0),
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        ISDELETED number(1,0) not null,
        CRON varchar2(30 char) not null,
        EXECUTIONDATE timestamp,
        TEMPLATE blob,
        MANUAL number(1,0),
        NAME varchar2(30 char) not null,
        VERSION number(10,0) not null,
        FKPROCESS number(19,0),
        primary key (ID)
    )
	TABLESPACE "@tables.tablespace@";

    create index IDX_HSEVDETAIL_DELETED on @database.schema@.HS_CA_EVENTDETAILS (ISDELETED)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_HSEVDETAIL_REFID on @database.schema@.HS_CA_EVENTDETAILS (REFID)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_HSEVDETAIL_VERSION on @database.schema@.HS_CA_EVENTDETAILS (VERSION)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_HSEVDETAIL_OPERDATE on @database.schema@.HS_CA_EVENTDETAILS (OPERATIONALDATE)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_HSEVTEXTDETAIL_VERSION on @database.schema@.HS_CA_EVENTEXTDETAILS (VERSION)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_HSEVTEXTDETAIL_DELETED on @database.schema@.HS_CA_EVENTEXTDETAILS (ISDELETED)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_HSEVTEXTDETAIL_REFID on @database.schema@.HS_CA_EVENTEXTDETAILS (REFID)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_HSEXTDETAIL_FKEVENTDETAIL on @database.schema@.HS_CA_EVENTEXTDETAILS (FKEVENTDETAIL)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_HSEVTEXTDETAIL_EXTENSIONID on @database.schema@.HS_CA_EVENTEXTDETAILS (EXTENSIONNAME, EXTENSIONNUMBER)
	TABLESPACE "@indexes.tablespace@";

    alter table @database.schema@.HS_CA_EVENTEXTDETAILS 
        add constraint FK_HSEXTENDED_DETAIL 
        foreign key (FKEVENTDETAIL) 
        references @database.schema@.HS_CA_EVENTDETAILS;

    create index IDX_HSEVMESGFLD_FKEVENTMESSAGE on @database.schema@.HS_CA_EVENTMESSAGEFIELDS (FKHSEVENTMESSAGE)
	TABLESPACE "@indexes.tablespace@";

    alter table @database.schema@.HS_CA_EVENTMESSAGEFIELDS 
        add constraint FK_HSEVENTFIELD_MESSAGE 
        foreign key (FKHSEVENTMESSAGE) 
        references @database.schema@.HS_CA_EVENTMESSAGES;

	alter table @database.schema@.TB_SP_SECURITY_PLANNING 
        add constraint FK_SECURITY_PLANNING 
        foreign key (FKSECURITY) 
        references @database.schema@.TB_SP_SECURITIES;

    alter table @database.schema@.TB_SP_SECURITY_PLANNING 
        add constraint FK_PLANNING 
        foreign key (FKPLANNING) 
        references @database.schema@.TB_SP_PLANNING_PROCESS;

    create index IDX_HSEVMESSAGE_FKEVENT on @database.schema@.HS_CA_EVENTMESSAGES (FKEVENT)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 8M 
        NEXT 800K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_HSEVMESSAGE_EFFECTIVEDATE on @database.schema@.HS_CA_EVENTMESSAGES (EFFECTIVEDATE)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 8M 
        NEXT 800K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_HSEVMESSAGE_CRTDATE on @database.schema@.HS_CA_EVENTMESSAGES (CRTDATE)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 8M 
        NEXT 800K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_HSEVMESSAGE_PROVIDER on @database.schema@.HS_CA_EVENTMESSAGES (FKPROVIDER)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 8M 
        NEXT 800K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_HSEVMESSAGE_MESSAGEID on @database.schema@.HS_CA_EVENTMESSAGES (MESSAGEID)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 8M 
        NEXT 800K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_HSEVMESSAGE_ISEXTENSION on @database.schema@.HS_CA_EVENTMESSAGES (ISEXTENSION)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 8M 
        NEXT 800K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_HSEVMESSAGE_OPERATIONNORM on @database.schema@.HS_CA_EVENTMESSAGES (OPERATIONNORM)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 8M 
        NEXT 800K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_HSEVMESSAGE_ISINPORTFOLIO on @database.schema@.HS_CA_EVENTMESSAGES (ISINPORTFOLIO)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 8M 
        NEXT 800K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_HSEVMESSAGE_FKFORMAT on @database.schema@.HS_CA_EVENTMESSAGES (FKFORMAT)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 8M 
        NEXT 800K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_HSEVMESSAGE_SENDTIMESTAMP on @database.schema@.HS_CA_EVENTMESSAGES (SENDERTIMESTAMP)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 8M 
        NEXT 800K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_HSEVMESSAGE_STATUS on @database.schema@.HS_CA_EVENTMESSAGES (ISENDED, FKSTATE)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 8M 
        NEXT 800K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_HSEVMESSAGE_FKSECURITY on @database.schema@.HS_CA_EVENTMESSAGES (FKSECURITY)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 8M 
        NEXT 800K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_HSEVMESSAGE_FKEVENTTYPE on @database.schema@.HS_CA_EVENTMESSAGES (FKEVENTTYPE)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 8M 
        NEXT 800K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_HSEVMESSAGE_DELETED on @database.schema@.HS_CA_EVENTMESSAGES (ISDELETED)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 8M 
        NEXT 800K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    alter table @database.schema@.HS_CA_EVENTMESSAGES 
        add constraint FK_HSEVENTMESSAGE_SECURITY 
        foreign key (FKSECURITY) 
        references @database.schema@.TB_SP_SECURITIES;

    alter table @database.schema@.HS_CA_EVENTMESSAGES 
        add constraint FK_HSEVENTMESSAGE_PROVIDER 
        foreign key (FKPROVIDER) 
        references @database.schema@.TB_CA_EVENTPROVIDERS;

    alter table @database.schema@.HS_CA_EVENTMESSAGES 
        add constraint FK_HSEVENTMESSAGE_TYPE 
        foreign key (FKTYPE, FKFORMAT) 
        references @database.schema@.TB_CA_MESSAGETYPES;

    alter table @database.schema@.HS_CA_EVENTMESSAGES 
        add constraint FK_HSEVENTMESSAGE_EVENTTYPE 
        foreign key (FKEVENTTYPE) 
        references @database.schema@.TB_CA_EVENTTYPES;

    alter table @database.schema@.HS_CA_EVENTMESSAGES 
        add constraint FK56046059FC4CA9B4 
        foreign key (FKSTATE, FKSTATEFLOW) 
        references @database.schema@.TB_STATES;

    create index IDX_HSEVENT_FKEVENTDETAIL on @database.schema@.HS_CA_EVENTS (FKEVENTDETAIL)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 14M 
        NEXT 800K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_HSEVENT_SECURITY on @database.schema@.HS_CA_EVENTS (FKSECURITY)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 14M 
        NEXT 800K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_HSEVENT_FKEVENTGROUP on @database.schema@.HS_CA_EVENTS (FKEVENTGROUP)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 14M 
        NEXT 800K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_HSEVENT_REFID on @database.schema@.HS_CA_EVENTS (REFID)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 14M 
        NEXT 800K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_HSEVENT_FKEVENTPROVIDER on @database.schema@.HS_CA_EVENTS (FKEVENTPROVIDER)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 14M 
        NEXT 800K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_HSEVENT_VERSION on @database.schema@.HS_CA_EVENTS (VERSION)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 14M 
        NEXT 800K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_HSEVENT_FKMASTEREVENT on @database.schema@.HS_CA_EVENTS (FKMASTEREVENT)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 14M 
        NEXT 800K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_HSEVENT_STATUS on @database.schema@.HS_CA_EVENTS (ISENDED, FKSTATEFLOW, FKSTATE)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 14M 
        NEXT 800K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_HSEVENT_OPERDATE on @database.schema@.HS_CA_EVENTS (OPERATIONALDATE)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 14M 
        NEXT 800K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_HSEVENT_DELETED on @database.schema@.HS_CA_EVENTS (ISDELETED)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 14M 
        NEXT 800K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_HSEVENT_EVENTTYPE on @database.schema@.HS_CA_EVENTS (FKEVENTTYPE)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 14M 
        NEXT 800K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_HSEVENT_EVENTCLASS on @database.schema@.HS_CA_EVENTS (EVENTCLASS)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 14M 
        NEXT 800K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    alter table @database.schema@.HS_CA_EVENTS 
        add constraint FK_HSEVENT_SECURITY 
        foreign key (FKSECURITY) 
        references @database.schema@.TB_SP_SECURITIES;

    alter table @database.schema@.HS_CA_EVENTS 
        add constraint FK_HSEVENT_EVENTDETAIL 
        foreign key (FKEVENTDETAIL) 
        references @database.schema@.HS_CA_EVENTDETAILS;

    alter table @database.schema@.HS_CA_EVENTS 
        add constraint FK_HSEVENT_PROVIDER 
        foreign key (FKEVENTPROVIDER) 
        references @database.schema@.TB_CA_EVENTPROVIDERS;

    alter table @database.schema@.HS_CA_EVENTS 
        add constraint FK_HSEVENT_EVENTTYPE 
        foreign key (FKEVENTTYPE) 
        references @database.schema@.TB_CA_EVENTTYPES;

    alter table @database.schema@.HS_CA_EVENTS 
        add constraint FK_HSGROUP_COLLECTED 
        foreign key (FKMASTEREVENT) 
        references @database.schema@.HS_CA_EVENTS;

    alter table @database.schema@.HS_CA_EVENTS 
        add constraint FK617DB5E6FC4CA9B4 
        foreign key (FKSTATE, FKSTATEFLOW) 
        references @database.schema@.TB_STATES;

    alter table @database.schema@.HS_CA_EVENTS 
        add constraint FK_HSCOLLECTED_GROUP 
        foreign key (FKEVENTGROUP) 
        references @database.schema@.HS_CA_EVENTS;

    create index IDX_HSSECPORT_DELETED on @database.schema@.HS_SP_SECURITYPORTFOLIO (ISDELETED)
	TABLESPACE "@indexes.tablespace@";

    alter table @database.schema@.HS_SP_SECURITYPORTFOLIO 
        add constraint FK_HS_SECPORT_SECURITY 
        foreign key (FKSECURITY) 
        references @database.schema@.TB_SP_SECURITIES;

    alter table @database.schema@.HS_SP_SECURITYPORTFOLIO 
        add constraint FK_HS_SECPORT_PORTFOLIO 
        foreign key (FKCUSTOMER) 
        references @database.schema@.TB_SP_CUSTOMERS;

    alter table @database.schema@.TB_ANSWERS 
        add constraint FK_POLL_ANSWER 
        foreign key (POLL_ID) 
        references @database.schema@.TB_POLL;

    alter table @database.schema@.TB_CATEGORIES 
        add constraint FKE9DA4C8D6F263114 
        foreign key (MASTER_CONTENT_ID) 
        references @database.schema@.TB_CONTENT;

    alter table @database.schema@.TB_CATEGORIES 
        add constraint FK_CATEGORY_PARENT 
        foreign key (PARENT_ID) 
        references @database.schema@.TB_CATEGORIES;

    alter table @database.schema@.TB_CATEGORY_PERMISSION 
        add constraint FK_CATEGORY_PERMISSION 
        foreign key (CATEGORY_ID) 
        references @database.schema@.TB_CATEGORIES;

    alter table @database.schema@.TB_CA_DOMAINCLUSTERFORMATS 
        add constraint FK_DOMCLSFORM_CLUSTER 
        foreign key (FKAPPLICATION, FKDOMAIN, FKDOMAINCLUSTER) 
        references @database.schema@.TB_DOMAINCLUSTERS;

    alter table @database.schema@.TB_CA_DOMAINCLUSTERFORMATS 
        add constraint FK_DOMCLSFORM_FORMAT 
        foreign key (FKMESSAGEFORMAT) 
        references @database.schema@.TB_CA_MESSAGEFORMATS;

    create index IDX_EVCONFFLD_FKEVTYPEDET on @database.schema@.TB_CA_EVENTCONFIGFIELDS (FKEVENTTYPEDETAIL)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVCONFFLD_FKEVCONF on @database.schema@.TB_CA_EVENTCONFIGFIELDS (FKEVENTCONFIG)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVCONFFLD_DELETED on @database.schema@.TB_CA_EVENTCONFIGFIELDS (ISDELETED)
	TABLESPACE "@indexes.tablespace@";

    alter table @database.schema@.TB_CA_EVENTCONFIGFIELDS 
        add constraint FK_EVNCONFFIELD_EVNTYPEDET 
        foreign key (FKEVENTTYPEDETAIL) 
        references @database.schema@.TB_CA_EVENTTYPEDETAILS;

    alter table @database.schema@.TB_CA_EVENTCONFIGFIELDS 
        add constraint FK_EVNCONFFIELD_EVENTCONFIG 
        foreign key (FKEVENTCONFIG) 
        references @database.schema@.TB_CA_EVENTCONFIGS;

    create index IDX_EVCONFMSGFLD_DELETED on @database.schema@.TB_CA_EVENTCONFIGMSGFLDS (ISDELETED)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVCONFMSGFLD_FKMESSAGETYPE on @database.schema@.TB_CA_EVENTCONFIGMSGFLDS (FKFORMAT, FKMSGTYPE)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVCONFMSGFLD_FKEVFILDCONF on @database.schema@.TB_CA_EVENTCONFIGMSGFLDS (FKEVENTFIELDCONFIG)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVCONFMSGFLD_FKEVPROVIDER on @database.schema@.TB_CA_EVENTCONFIGMSGFLDS (FKEVENTPROVIDER)
	TABLESPACE "@indexes.tablespace@";

    alter table @database.schema@.TB_CA_EVENTCONFIGMSGFLDS 
        add constraint FK_EVNCONFFLDPRV_EVENTPROV 
        foreign key (FKEVENTPROVIDER) 
        references @database.schema@.TB_CA_EVENTPROVIDERS;

    alter table @database.schema@.TB_CA_EVENTCONFIGMSGFLDS 
        add constraint FK_EVNCONFFLDPRV_EVNCONFFLD 
        foreign key (FKEVENTFIELDCONFIG) 
        references @database.schema@.TB_CA_EVENTCONFIGFIELDS;

    alter table @database.schema@.TB_CA_EVENTCONFIGMSGFLDS 
        add constraint FK_EVNCONFFLDMT_EVENTMESG 
        foreign key (FKMSGTYPE, FKFORMAT) 
        references @database.schema@.TB_CA_MESSAGETYPES;

    create index IDX_EVCONFIG_DELETED on @database.schema@.TB_CA_EVENTCONFIGS (ISDELETED)
	TABLESPACE "@indexes.tablespace@";

    alter table @database.schema@.TB_CA_EVENTCONFIGS 
        add constraint FK_EVENTCONFIG_EVENTPROVIDER1 
        foreign key (FKPREFPROVIDER1) 
        references @database.schema@.TB_CA_EVENTPROVIDERS;

    alter table @database.schema@.TB_CA_EVENTCONFIGS 
        add constraint FK_EVENTCONFIG_EVENTPROVIDER2 
        foreign key (FKSECPREFPROV2) 
        references @database.schema@.TB_CA_EVENTPROVIDERS;

    alter table @database.schema@.TB_CA_EVENTCONFIGS 
        add constraint FK_EVENTCONFIG_EVENTTYPE 
        foreign key (FKEVENTTYPE) 
        references @database.schema@.TB_CA_EVENTTYPES;

    create index IDX_EVDETAIL_REFID on @database.schema@.TB_CA_EVENTDETAILS (REFID)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVDETAIL_VERSION on @database.schema@.TB_CA_EVENTDETAILS (VERSION)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVDETAIL_OPERDATE on @database.schema@.TB_CA_EVENTDETAILS (OPERATIONALDATE)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVDETAIL_DELETED on @database.schema@.TB_CA_EVENTDETAILS (ISDELETED)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVTEXTDETAIL_VERSION on @database.schema@.TB_CA_EVENTEXTDETAILS (VERSION)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVTEXTDETAIL_EXTENSIONID on @database.schema@.TB_CA_EVENTEXTDETAILS (EXTENSIONNAME, EXTENSIONNUMBER)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVTEXTDETAIL_REFID on @database.schema@.TB_CA_EVENTEXTDETAILS (REFID)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVTEXTDETAIL_FKEVENTDETAIL on @database.schema@.TB_CA_EVENTEXTDETAILS (FKEVENTDETAIL)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVTEXTDETAIL_DELETED on @database.schema@.TB_CA_EVENTEXTDETAILS (ISDELETED)
	TABLESPACE "@indexes.tablespace@";

    alter table @database.schema@.TB_CA_EVENTEXTDETAILS 
        add constraint FK_EXTENDED_DETAIL 
        foreign key (FKEVENTDETAIL) 
        references @database.schema@.TB_CA_EVENTDETAILS;

    create index IDX_EVMESGFLD_FKEVENTMESSAGE on @database.schema@.TB_CA_EVENTMESSAGEFIELDS (FKEVENTMESSAGE)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 104M 
        NEXT 11M 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    alter table @database.schema@.TB_CA_EVENTMESSAGEFIELDS 
        add constraint FK_EVENTFIELD_MESSAGE 
        foreign key (FKEVENTMESSAGE) 
        references @database.schema@.TB_CA_EVENTMESSAGES;

    create index IDX_EVMESSAGE_ISINPORTFOLIO on @database.schema@.TB_CA_EVENTMESSAGES (ISINPORTFOLIO)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 4M 
        NEXT 400K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_EVMESSAGE_CRTDATE on @database.schema@.TB_CA_EVENTMESSAGES (CRTDATE)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 4M 
        NEXT 400K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_EVMESSAGE_FKEVENT on @database.schema@.TB_CA_EVENTMESSAGES (FKEVENT)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 4M 
        NEXT 400K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_EVMESSAGE_PROVIDER on @database.schema@.TB_CA_EVENTMESSAGES (FKPROVIDER)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 4M 
        NEXT 400K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_EVMESSAGE_FKEVENTTYPE on @database.schema@.TB_CA_EVENTMESSAGES (FKEVENTTYPE)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 4M 
        NEXT 400K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_EVMESSAGE_ISEXTENSION on @database.schema@.TB_CA_EVENTMESSAGES (ISEXTENSION)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 4M 
        NEXT 400K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_EVMESSAGE_FKFORMAT on @database.schema@.TB_CA_EVENTMESSAGES (FKFORMAT)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 4M 
        NEXT 400K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_EVMESSAGE_EXTENSIONREF on @database.schema@.TB_CA_EVENTMESSAGES (EXTENSIONREF)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 4M 
        NEXT 400K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_EVMESSAGE_STATUS on @database.schema@.TB_CA_EVENTMESSAGES (ISENDED, FKSTATE)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 4M 
        NEXT 400K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_EVMESSAGE_SENDTIMESTAMP on @database.schema@.TB_CA_EVENTMESSAGES (SENDERTIMESTAMP)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 4M 
        NEXT 400K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_EVMESSAGE_DELETED on @database.schema@.TB_CA_EVENTMESSAGES (ISDELETED)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 4M 
        NEXT 400K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_EVMESSAGE_OPERATIONNORM on @database.schema@.TB_CA_EVENTMESSAGES (OPERATIONNORM)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 4M 
        NEXT 400K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_EVMESSAGE_FKSECURITY on @database.schema@.TB_CA_EVENTMESSAGES (FKSECURITY)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 4M 
        NEXT 400K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_EVMESSAGE_MESSAGEID on @database.schema@.TB_CA_EVENTMESSAGES (MESSAGEID)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 4M 
        NEXT 400K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_EVMESSAGE_EFFECTIVEDATE on @database.schema@.TB_CA_EVENTMESSAGES (EFFECTIVEDATE)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 4M 
        NEXT 400K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    alter table @database.schema@.TB_CA_EVENTMESSAGES 
        add constraint FK_EVENTMESSAGE_SECURITY 
        foreign key (FKSECURITY) 
        references @database.schema@.TB_SP_SECURITIES;

    alter table @database.schema@.TB_CA_EVENTMESSAGES 
        add constraint FK_EVENTMESSAGE_PROVIDER 
        foreign key (FKPROVIDER) 
        references @database.schema@.TB_CA_EVENTPROVIDERS;

    alter table @database.schema@.TB_CA_EVENTMESSAGES 
        add constraint FK_EVENTMESSAGE_TYPE 
        foreign key (FKTYPE, FKFORMAT) 
        references @database.schema@.TB_CA_MESSAGETYPES;

    alter table @database.schema@.TB_CA_EVENTMESSAGES 
        add constraint FK_EVENTMESSAGE_EVENTTYPE 
        foreign key (FKEVENTTYPE) 
        references @database.schema@.TB_CA_EVENTTYPES;

    alter table @database.schema@.TB_CA_EVENTMESSAGES 
        add constraint FKEC50F156FC4CA9B4 
        foreign key (FKSTATE, FKSTATEFLOW) 
        references @database.schema@.TB_STATES;

    alter table @database.schema@.TB_CA_EVENTMRCONFIGS 
        add constraint FK_EVENTMRCONFIGS_EVNTYPEDET 
        foreign key (FKEVENTTYPEDETAIL) 
        references @database.schema@.TB_CA_EVENTTYPEDETAILS;

    alter table @database.schema@.TB_CA_EVENTMRCONFIGS 
        add constraint FK_EVENTMRCONFIGS_EVENTCONFIG 
        foreign key (FKEVENTCONFIG) 
        references @database.schema@.TB_CA_EVENTCONFIGS;

    create index IDX_EVPROVIDER_EVENTPROVCLASS on @database.schema@.TB_CA_EVENTPROVIDERS (EVENTPROVCLASS)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVPROVIDER_DELETED on @database.schema@.TB_CA_EVENTPROVIDERS (ISDELETED)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVENT_FKMASTEREVENT on @database.schema@.TB_CA_EVENTS (FKMASTEREVENT)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVENT_FKEVENTDETAIL on @database.schema@.TB_CA_EVENTS (FKEVENTDETAIL)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVENT_FKEVENTPROVIDER on @database.schema@.TB_CA_EVENTS (FKEVENTPROVIDER)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVENT_EVENTCLASS on @database.schema@.TB_CA_EVENTS (EVENTCLASS)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVENT_FKEVENTGROUP on @database.schema@.TB_CA_EVENTS (FKEVENTGROUP)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVENT_OPERDATE on @database.schema@.TB_CA_EVENTS (OPERATIONALDATE)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVENT_SECURITY on @database.schema@.TB_CA_EVENTS (FKSECURITY)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVENT_DELETED on @database.schema@.TB_CA_EVENTS (ISDELETED)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVENT_REFID on @database.schema@.TB_CA_EVENTS (REFID)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVENT_STATUS on @database.schema@.TB_CA_EVENTS (ISENDED, FKSTATEFLOW, FKSTATE)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVENT_EVENTTYPE on @database.schema@.TB_CA_EVENTS (FKEVENTTYPE)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVENT_VERSION on @database.schema@.TB_CA_EVENTS (VERSION)
	TABLESPACE "@indexes.tablespace@";

    alter table @database.schema@.TB_CA_EVENTS 
        add constraint FK_EVENT_SECURITY 
        foreign key (FKSECURITY) 
        references @database.schema@.TB_SP_SECURITIES;

    alter table @database.schema@.TB_CA_EVENTS 
        add constraint FK_EVENT_EVENTDETAIL 
        foreign key (FKEVENTDETAIL) 
        references @database.schema@.TB_CA_EVENTDETAILS;

    alter table @database.schema@.TB_CA_EVENTS 
        add constraint FK_EVENT_PROVIDER 
        foreign key (FKEVENTPROVIDER) 
        references @database.schema@.TB_CA_EVENTPROVIDERS;

    alter table @database.schema@.TB_CA_EVENTS 
        add constraint FK_EVENT_EVENTTYPE 
        foreign key (FKEVENTTYPE) 
        references @database.schema@.TB_CA_EVENTTYPES;

    alter table @database.schema@.TB_CA_EVENTS 
        add constraint FKDAA19789FC4CA9B4 
        foreign key (FKSTATE, FKSTATEFLOW) 
        references @database.schema@.TB_STATES;

    alter table @database.schema@.TB_CA_EVENTS 
        add constraint FK_GROUP_COLLECTED 
        foreign key (FKMASTEREVENT) 
        references @database.schema@.TB_CA_EVENTS;

    alter table @database.schema@.TB_CA_EVENTS 
        add constraint FK_COLLECTED_GROUP 
        foreign key (FKEVENTGROUP) 
        references @database.schema@.TB_CA_EVENTS;

    create index IDX_EVTYPEDET_ISDISPLAYINTABLE on @database.schema@.TB_CA_EVENTTYPEDETAILS (ISDISPLAYINTABLE)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVTYPEDET_FKEVENTTYPE on @database.schema@.TB_CA_EVENTTYPEDETAILS (FKEVENTTYPE)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVTYPEDET_DELETED on @database.schema@.TB_CA_EVENTTYPEDETAILS (ISDELETED)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_EVTYPEDET_FIELDPATH on @database.schema@.TB_CA_EVENTTYPEDETAILS (FIELDPATH)
	TABLESPACE "@indexes.tablespace@";

    alter table @database.schema@.TB_CA_EVENTTYPEDETAILS 
        add constraint FK_DETAIL_EVENTTYPE 
        foreign key (FKEVENTTYPE) 
        references @database.schema@.TB_CA_EVENTTYPES;

    create index IDX_FORMATPROV_FKPROVIDER on @database.schema@.TB_CA_FORMATPROVIDERS (FKEVENTPROVIDER)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_FORMATPROV_FKFORMAT on @database.schema@.TB_CA_FORMATPROVIDERS (FKMESSAGEFORMAT, IDATMESSAGE)
	TABLESPACE "@indexes.tablespace@";

    alter table @database.schema@.TB_CA_FORMATPROVIDERS 
        add constraint FK_FORMATPROV_PROVIDER 
        foreign key (FKEVENTPROVIDER) 
        references @database.schema@.TB_CA_EVENTPROVIDERS;

    alter table @database.schema@.TB_CA_FORMATPROVIDERS 
        add constraint FK_FORMATPROV_FORMAT 
        foreign key (FKMESSAGEFORMAT) 
        references @database.schema@.TB_CA_MESSAGEFORMATS;

    alter table @database.schema@.TB_CA_MESSAGEFIELDCONFIGS 
        add constraint FK_FIELD_TYPE 
        foreign key (FKTYPE, FKFORMAT) 
        references @database.schema@.TB_CA_MESSAGETYPES;

    create index IDX_MESGFORMAT_DELETED on @database.schema@.TB_CA_MESSAGEFORMATS (ISDELETED)
	TABLESPACE "@indexes.tablespace@";

    alter table @database.schema@.TB_CA_MESSAGETYPES 
        add constraint FK_TYPE_FORMAT 
        foreign key (FKFORMAT) 
        references @database.schema@.TB_CA_MESSAGEFORMATS;

    create index IDX_CONFIG_DELETED on @database.schema@.TB_CONFIGS (ISDELETED)
	TABLESPACE "@indexes.tablespace@";

    alter table @database.schema@.TB_CONFIGS 
        add constraint FK_CONFIG_APPLICATION 
        foreign key (FKAPPLICATION) 
        references @database.schema@.TB_APPLICATIONS;

    alter table @database.schema@.TB_CONTAINER_SCHEDULE 
        add constraint FK_SCHEDULE_HTML 
        foreign key (CONTENT_ID) 
        references @database.schema@.TB_CONTENT;

    alter table @database.schema@.TB_CONTAINER_SCHEDULE 
        add constraint FK_CONTAINER_SCHEDULE 
        foreign key (CONTAINER_NAME, CONTAINER_TYPE) 
        references @database.schema@.TB_CONTAINER;

    alter table @database.schema@.TB_CONTENT 
        add constraint FK_CONTENT_STATUS 
        foreign key (STATUS_ID) 
        references @database.schema@.TB_CONTENT_STATUS;

    alter table @database.schema@.TB_CONTENT 
        add constraint FK_MASTER_CONTENT 
        foreign key (MASTER_CONTENT_ID) 
        references @database.schema@.TB_CONTENT;

    alter table @database.schema@.TB_CONTENTS_CATEGORIES 
        add constraint FK_CONTENTS_CATEGORIES 
        foreign key (CONTENT_ID) 
        references @database.schema@.TB_CONTENT;

    alter table @database.schema@.TB_CONTENTS_CATEGORIES 
        add constraint FK_CATEGORIES_CONTENTS 
        foreign key (CATEGORY_ID) 
        references @database.schema@.TB_CATEGORIES;

    alter table @database.schema@.TB_CONTENT_PERMISSION 
        add constraint FK_CONTENT_PERMISSION 
        foreign key (CONTENT_ID) 
        references @database.schema@.TB_CONTENT;

    alter table @database.schema@.TB_DIARY 
        add constraint FK_DIARY_CONTENT 
        foreign key (CONTENT_ID) 
        references @database.schema@.TB_CONTENT;

    alter table @database.schema@.TB_DOCUMENTS 
        add constraint FK_DOCUMENT_CONTENT 
        foreign key (CONTENT_ID) 
        references @database.schema@.TB_CONTENT;

    alter table @database.schema@.TB_DOCUMENT_VERSIONS 
        add constraint FK_DOC_ATTACHMENT 
        foreign key (ATTACHED_TO) 
        references @database.schema@.TB_DOCUMENT_VERSIONS;

    alter table @database.schema@.TB_DOCUMENT_VERSIONS 
        add constraint FK_DOC_VERSION_CONTENT 
        foreign key (CONTENT_ID) 
        references @database.schema@.TB_CONTENT;

    alter table @database.schema@.TB_DOCUMENT_VERSIONS 
        add constraint FK_VERSION_MIME_TYPE 
        foreign key (MIME_TYPE) 
        references @database.schema@.TB_MIME_TYPES;

    alter table @database.schema@.TB_DOCUMENT_VERSIONS 
        add constraint FK_VERSION_DOC 
        foreign key (DOCUMENT_ID) 
        references @database.schema@.TB_DOCUMENTS;

    create index IDX_DOMCLUSTER_DELETED on @database.schema@.TB_DOMAINCLUSTERS (ISDELETED)
	TABLESPACE "@indexes.tablespace@";

    alter table @database.schema@.TB_DOMAINCLUSTERS 
        add constraint FK_DOMAINCLUSTER_DOMAIN 
        foreign key (FKAPPLICATION, FKDOMAIN) 
        references @database.schema@.TB_DOMAINS;

    create index IDX_DOMNORM_DELETED on @database.schema@.TB_DOMAINNORMS (ISDELETED)
	TABLESPACE "@indexes.tablespace@";

    alter table @database.schema@.TB_DOMAINNORMS 
        add constraint FK_DOMAINNORM_DOMAIN 
        foreign key (FKAPPLICATION, FKDOMAIN) 
        references @database.schema@.TB_DOMAINS;

    create index IDX_DOMAIN_DELETED on @database.schema@.TB_DOMAINS (ISDELETED)
	TABLESPACE "@indexes.tablespace@";

    alter table @database.schema@.TB_DOMAINS 
        add constraint FK_DOMAIN_APPLICATION 
        foreign key (FKAPPLICATION) 
        references @database.schema@.TB_APPLICATIONS;

    create index IDX_DOMVALUE_DELETED on @database.schema@.TB_DOMAINVALUES (ISDELETED)
	TABLESPACE "@indexes.tablespace@";

    alter table @database.schema@.TB_DOMAINVALUES 
        add constraint FK_VALUE_CLUSTER 
        foreign key (FKCLTAPPLICATION, FKCLTDOMAIN, FKCLUSTER) 
        references @database.schema@.TB_DOMAINCLUSTERS;

    alter table @database.schema@.TB_DOMAINVALUES 
        add constraint FK_VALUE_NORM 
        foreign key (FKNRMAPPLICATION, FKNRMDOMAIN, FKNORM) 
        references @database.schema@.TB_DOMAINNORMS;

    create index IDX_STATE_FKAPPLICATION on @database.schema@.TB_FLOWS (FKAPPLICATION)
	TABLESPACE "@indexes.tablespace@";

    alter table @database.schema@.TB_FLOWS 
        add constraint FK_FLOW_APPLICATION 
        foreign key (FKAPPLICATION) 
        references @database.schema@.TB_APPLICATIONS;

    alter table @database.schema@.TB_FUNCTIONS 
        add constraint FK_FUNCTION_MODULE 
        foreign key (MODULE_ID) 
        references @database.schema@.TB_MODULES;

    alter table @database.schema@.TB_FUNCTION_GROUPS 
        add constraint FK_GROUP_FUNCTION 
        foreign key (FUNCTION_ID, MODULE_ID) 
        references @database.schema@.TB_FUNCTIONS;

    alter table @database.schema@.TB_FUNCTION_GROUPS 
        add constraint FK_FUNCTION_GROUP 
        foreign key (GROUP_ID) 
        references @database.schema@.TB_GROUPS;

    alter table @database.schema@.TB_GROUPS 
        add constraint FK_GROUP_TYPE 
        foreign key (GROUP_TYPE) 
        references @database.schema@.TB_GROUP_TYPES;

    alter table @database.schema@.TB_HTML 
        add constraint FK_HTML_CONTENT 
        foreign key (CONTENT_ID) 
        references @database.schema@.TB_CONTENT;

    create index IDX_LOGFIELDUPDATE_FKLOG on @database.schema@.TB_LOGFIELDUPDATES (FKLOG)
	TABLESPACE "@indexes.tablespace@";

    alter table @database.schema@.TB_LOGFIELDUPDATES 
        add constraint FK_LOGFIELDUPDATE_LOG 
        foreign key (FKLOG) 
        references @database.schema@.TB_LOGS;

    create index IDX_LOG_LOGCLASS on @database.schema@.TB_LOGS (LOGCLASS)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 7M 
        NEXT 700K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    create index IDX_LOG_CRTDATE on @database.schema@.TB_LOGS (CRTDATE)
	TABLESPACE "@indexes.tablespace@"
	STORAGE ( 
		INITIAL 3M 
        NEXT 700K 
		MAXEXTENTS UNLIMITED 
		PCTINCREASE 10
	);

    alter table @database.schema@.TB_MENU_ITEMS 
        add constraint FK_MENUITEMS_BOXLET 
        foreign key (CONTAINER_NAME, CONTAINER_TYPE) 
        references @database.schema@.TB_CONTAINER;

    alter table @database.schema@.TB_MENU_ITEMS 
        add constraint FK_MENUITEMS_CATEGORY 
        foreign key (CATEGORY_ID) 
        references @database.schema@.TB_CATEGORIES;

    alter table @database.schema@.TB_MENU_ITEMS 
        add constraint FKCD87B7318D188391 
        foreign key (PARENT) 
        references @database.schema@.TB_MENU_ITEMS;

    alter table @database.schema@.TB_MENU_ITEM_GROUP 
        add constraint FK41D94542844EA99B 
        foreign key (MENU_ITEM_ID) 
        references @database.schema@.TB_MENU_ITEMS;

    alter table @database.schema@.TB_MENU_ITEM_GROUP 
        add constraint FK41D94542CA0DE70E 
        foreign key (GROUP_ID) 
        references @database.schema@.TB_GROUPS;

    alter table @database.schema@.TB_MESSAGE 
        add constraint FK_USER_MSG_CONTENT 
        foreign key (CONTENT_ID) 
        references @database.schema@.TB_CONTENT;

    alter table @database.schema@.TB_MODULES 
        add constraint FK_MODULE_TYPE 
        foreign key (MODULE_TYPE) 
        references @database.schema@.TB_MODULE_TYPES;

    alter table @database.schema@.TB_NEWS 
        add constraint FK_NEWS_CONTENT 
        foreign key (CONTENT_ID) 
        references @database.schema@.TB_CONTENT;

    alter table @database.schema@.TB_NEWS_LINK 
        add constraint FK_NEWS_LINK 
        foreign key (NEWS_ID) 
        references @database.schema@.TB_NEWS;

    alter table @database.schema@.TB_NEWS_PICTURE 
        add constraint FK_NEWS_PICTURE 
        foreign key (NEWS_ID) 
        references @database.schema@.TB_NEWS;

    alter table @database.schema@.TB_OLD_PASSWORDS 
        add constraint FK_USER_OLD_PSWD 
        foreign key (USER_ID) 
        references @database.schema@.TB_USER_PROFILES;

    alter table @database.schema@.TB_PAGES 
        add constraint FK_SESSION_PAGES 
        foreign key (TRACKING_ID) 
        references @database.schema@.TB_TRACKING;

    alter table @database.schema@.TB_POLL 
        add constraint FK_POLL_CONTENT 
        foreign key (CONTENT_ID) 
        references @database.schema@.TB_CONTENT;

    alter table @database.schema@.TB_POLL_USER 
        add constraint FK_POLL_USER 
        foreign key (POLL_ID) 
        references @database.schema@.TB_POLL;

    alter table @database.schema@.TB_POPUP_SCHEDULE 
        add constraint FK_POPUP_SCHEDULE 
        foreign key (SCHEDULE_ID) 
        references @database.schema@.TB_CONTAINER_SCHEDULE;

    alter table @database.schema@.TB_SECURITY_CONSTRAINT 
        add constraint FK_GROUP_RES_COLL 
        foreign key (COLLECTION_NAME) 
        references @database.schema@.TB_RESOURCE_COLLECTION;

    alter table @database.schema@.TB_SECURITY_CONSTRAINT 
        add constraint FK_RES_COLL_GROUP 
        foreign key (GROUP_ID) 
        references @database.schema@.TB_GROUPS;

    create index IDX_MANAGERGROUP_DELETED on @database.schema@.TB_SP_MANAGERGROUPS (ISDELETED)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_MANAGER_DELETED on @database.schema@.TB_SP_MANAGERS (ISDELETED)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_MANAGER_FKMANAGERGROUP on @database.schema@.TB_SP_MANAGERS (FKMANAGERGROUP)
	TABLESPACE "@indexes.tablespace@";

    alter table @database.schema@.TB_SP_MANAGERS 
        add constraint FK_MANAGER_MANAGERGROUP 
        foreign key (FKMANAGERGROUP) 
        references @database.schema@.TB_SP_MANAGERGROUPS;

    create index IDX_MARKET_DELETED on @database.schema@.TB_SP_MARKETS (ISDELETED)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_MARKET_COUNTRY on @database.schema@.TB_SP_MARKETS (COUNTRY)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_MARKET_MIC on @database.schema@.TB_SP_MARKETS (MIC)
	TABLESPACE "@indexes.tablespace@";

	alter table @database.schema@.TB_SP_PLANNING_PROCESS 
        add constraint FK_PROCESS 
        foreign key (FKPROCESS) 
        references @database.schema@.TB_SP_PROCESS;

	alter table @database.schema@.TB_SP_SECURITIES 
        add constraint FK_PLANIFICATION 
        foreign key (FKPLANIFICATION) 
        references @database.schema@.TB_SP_PLANNING_PROCESS;

    create index IDX_CUSTOMER_FKMANAGERGROUP on @database.schema@.TB_SP_CUSTOMERS (FKCUSTMANAGERGROUP)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_CUSTOMER_DELETED on @database.schema@.TB_SP_CUSTOMERS (ISDELETED)
	TABLESPACE "@indexes.tablespace@";

    alter table @database.schema@.TB_SP_CUSTOMERS 
        add constraint FK_CUSTOMER_MANAGERGROUP 
        foreign key (FKCUSTMANAGERGROUP) 
        references @database.schema@.TB_SP_MANAGERGROUPS;

	alter table @database.schema@.TB_SP_CUSTOMERS 
        add constraint FK_CUSTOMER_MANAGER 
        foreign key (FKCUSTMANAGER) 
        references @database.schema@.TB_SP_MANAGERS;

    create index IDX_SECURITY_SEDOL on @database.schema@.TB_SP_SECURITIES (SEDOL)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_SECURITY_INCUSTPORTF on @database.schema@.TB_SP_SECURITIES (INCUSTPORTFOLIO)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_SECURITY_INFPROV2 on @database.schema@.TB_SP_SECURITIES (PROVIDER_ID2)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_SECURITY_DELETED on @database.schema@.TB_SP_SECURITIES (ISDELETED)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_SECURITY_CUSIP on @database.schema@.TB_SP_SECURITIES (CUSIP)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_SECURITY_ISIN on @database.schema@.TB_SP_SECURITIES (ISIN)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_SECURITY_SECURITYCLASS on @database.schema@.TB_SP_SECURITIES (SECURITYCLASS)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_SECURITY_INFPROV3 on @database.schema@.TB_SP_SECURITIES (PROVIDER_ID3)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_SECURITY_FKMARKET on @database.schema@.TB_SP_SECURITIES (FKMARKET)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_SECURITY_TICKER on @database.schema@.TB_SP_SECURITIES (TICKER)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_SECURITY_INFPROV1 on @database.schema@.TB_SP_SECURITIES (PROVIDER_ID1)
	TABLESPACE "@indexes.tablespace@";

    alter table @database.schema@.TB_SP_SECURITIES 
        add constraint FK_FINANCIAL_ASSET 
        foreign key (FKFINANCIALASSET) 
        references @database.schema@.TB_SP_SECURITY_FASSETS;

    alter table @database.schema@.TB_SP_SECURITIES 
        add constraint FK_SECURITY_DETAIL 
        foreign key (FKDETAIL) 
        references @database.schema@.TB_SP_SECURITYDETAILS;

    alter table @database.schema@.TB_SP_SECURITIES 
        add constraint FK_SECURITY_MARKET 
        foreign key (FKMARKET) 
        references @database.schema@.TB_SP_MARKETS;

    create index IDX_SECDETAIL_DELETED on @database.schema@.TB_SP_SECURITYDETAILS (ISDELETED)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_SECDETAIL_VERSION on @database.schema@.TB_SP_SECURITYDETAILS (VERSION)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_SECPORT_EMISSIONDATE on @database.schema@.TB_SP_SECURITYPORTFOLIO (EMISSIONDATE)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_SECPORT_FKCUSTOMER on @database.schema@.TB_SP_SECURITYPORTFOLIO (FKCUSTOMER)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_SECPORT_DELETED on @database.schema@.TB_SP_SECURITYPORTFOLIO (ISDELETED)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_SECPORT_VERSION on @database.schema@.TB_SP_SECURITYPORTFOLIO (VERSION)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_SECPORT_FKSECURITY on @database.schema@.TB_SP_SECURITYPORTFOLIO (FKSECURITY)
	TABLESPACE "@indexes.tablespace@";

    alter table @database.schema@.TB_SP_SECURITYPORTFOLIO 
        add constraint FK_SECPORT_SECURITY 
        foreign key (FKSECURITY) 
        references @database.schema@.TB_SP_SECURITIES;

    alter table @database.schema@.TB_SP_SECURITYPORTFOLIO 
        add constraint FK_SECPORT_PROVACCOUNTS 
        foreign key (FKPROVIDERACCOUNT) 
        references @database.schema@.TB_SP_CUSTODIAN_ACCOUNTS;

    create index IDX_SECURITYFADET_NAME on @database.schema@.TB_SP_SECURITY_FASSETS_DETAILS (NAME)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_SECURITYFADET_FA on @database.schema@.TB_SP_SECURITY_FASSETS_DETAILS (FKSECURITYFA)
	TABLESPACE "@indexes.tablespace@";

    alter table @database.schema@.TB_SP_SECURITY_FASSETS_DETAILS 
        add constraint FK_DETAIL_SECFA 
        foreign key (FKSECURITYFA) 
        references @database.schema@.TB_SP_SECURITY_FASSETS;

    create index IDX_SECURITYFA_NAME on @database.schema@.TB_SP_SECURITY_FASSETS (NAME)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_STATE_CODE on @database.schema@.TB_STATES (CODE)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_STATE_FKFLOW on @database.schema@.TB_STATES (FKFLOW)
	TABLESPACE "@indexes.tablespace@";

    alter table @database.schema@.TB_STATES 
        add constraint FK_STATE_FLOW 
        foreign key (FKFLOW) 
        references @database.schema@.TB_FLOWS;

    create index IDX_TRANSITION_CODE on @database.schema@.TB_TRANSITIONS (CODE)
	TABLESPACE "@indexes.tablespace@";

    create index IDX_TRANSITION_FKFLOW on @database.schema@.TB_TRANSITIONS (FKFLOW)
	TABLESPACE "@indexes.tablespace@";

    alter table @database.schema@.TB_TRANSITIONS 
        add constraint FK_TRANSITION_FLOW 
        foreign key (FKFLOW) 
        references @database.schema@.TB_FLOWS;

    alter table @database.schema@.TB_TRANSITIONS 
        add constraint FK_TRANSITION_ENDSTATE 
        foreign key (FKENDSTATE, FKENDSTATEFLOW) 
        references @database.schema@.TB_STATES;

    alter table @database.schema@.TB_TRANSITIONS 
        add constraint FK_TRANSITION_INITSTATE 
        foreign key (FKINITIALSTATE, FKINITIALSTATEFLOW) 
        references @database.schema@.TB_STATES;

    alter table @database.schema@.TB_USER_GROUP 
        add constraint FK_USER_GROUP 
        foreign key (USER_ID) 
        references @database.schema@.TB_USER_PROFILES;

    alter table @database.schema@.TB_USER_PROFILES 
        add constraint FK_USER_TYPE 
        foreign key (USER_TYPE) 
        references @database.schema@.TB_USER_TYPE;

	create index IDX_PROCESS on @database.schema@.TB_SP_PLANNING_PROCESS (FKPROCESS);

	create sequence @database.schema@.CAALERTS_GEN;	

    create sequence @database.schema@.CAEVDETEXT_GEN;

    create sequence @database.schema@.CAEVENTCONF_GEN;

    create sequence @database.schema@.CAEVENTDETAIL_GEN;

    create sequence @database.schema@.CAEVENTMESS_GEN;

    create sequence @database.schema@.CAEVENTMRCONF_GEN;

    create sequence @database.schema@.CAEVENT_GEN;

    create sequence @database.schema@.CAEVFIELDCONF_GEN;

    create sequence @database.schema@.CAEVFLDMESSCONF_GEN;

    create sequence @database.schema@.CAEVMESSFLD_GEN;

    create sequence @database.schema@.CAEVTYPEDET_GEN;

    create sequence @database.schema@.CAFORMATPROV_GEN;

    create sequence @database.schema@.CASPSECURITYDETAIL_GEN;

    create sequence @database.schema@.SPMARKET_GEN;

    create sequence @database.schema@.SPPERSECURITYPROC_GEN;

    create sequence @database.schema@.SPPERSECURITY_GEN;

    create sequence @database.schema@.SPSECFADET_GEN;

    create sequence @database.schema@.SPSECPORT_GEN;

    create sequence @database.schema@.SPSECURITY_GEN;

    create sequence @database.schema@.TBLOGFIELD_GEN;

    create sequence @database.schema@.TBLOG_GEN;

    create sequence @database.schema@.hibernate_sequence;

	create sequence @database.schema@.SPPROCESS_GEN;

	create sequence @database.schema@.SPPLANIFICATION_GEN;

	create sequence @database.schema@.SPPERSECURITYPLANIFICATION_GEN;


	create table @database.schema@.TB_CA_EVENTHOLDINGANSWER (
        ID number(19,0) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        UPDDATE timestamp not null,
        ISDELETED number(1,0) not null,
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
        CHAR1 char(1 char),
        CHAR2 char(1 char),
        CHAR3 char(1 char),
        CHAR4 char(1 char),
        CHAR5 char(1 char),
        CHAR6 char(1 char),
        CHAR7 char(1 char),
        CHAR8 char(1 char),
        CHAR9 char(1 char),
        CHAR10 char(1 char),
        VERSION number(10,0) not null,
        FKHOLDING number(19,0) not null,
        FKMASTEREVENT number(19,0) not null,
        primary key (ID)
    )TABLESPACE "@tables.tablespace@";
	
    create index IDX_EVHOLANSW_VERSION on @database.schema@.TB_CA_EVENTHOLDINGANSWER (VERSION) TABLESPACE "@indexes.tablespace@";

    create index IDX_EVHOLANSW_DELETED on @database.schema@.TB_CA_EVENTHOLDINGANSWER (ISDELETED) TABLESPACE "@indexes.tablespace@";

    alter table @database.schema@.TB_CA_EVENTHOLDINGANSWER 
        add constraint FK_HOLDING 
        foreign key (FKHOLDING) 
        references @database.schema@.TB_SP_SECURITYPORTFOLIO;

    alter table @database.schema@.TB_CA_EVENTHOLDINGANSWER 
        add constraint FK_MASTEREVENT 
        foreign key (FKMASTEREVENT) 
        references @database.schema@.TB_CA_EVENTS;
		
	create sequence @database.schema@.CAEVENTHOLDINGANSWER_GEN;

	create table @database.schema@.TB_CA_QUESTIONS (
        ID number(19,0) not null,
        ISOPTIONAL number(1,0) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        UPDUSER varchar2(128 char) not null,
        UPDDATE timestamp not null,
        ISDELETED number(1,0) not null,
        ISHEADER number(1,0) not null,
        HEADERGROUP number(19,0) not null,
        ISHIDDEN number(1,0) not null,
        TEXT varchar2(256 char) not null,
		DESCRIPTION varchar2(256 char),
		DEFAULTVALUE varchar2(256 char),
        VERSION number(10,0) not null,
        POSITION number(10,0) not null,
		HEADERPOSITION number(10,0) not null,
        FORMAT varchar2(64 char),
        FKANSWERTYPE varchar2(16 char),
        FKEVENT number(19,0) not null,
        primary key (ID)
    )TABLESPACE "@tables.tablespace@";

    create table @database.schema@.TB_CA_ANSWERTYPES (
        ID varchar2(16 char) not null,
        CRTUSER varchar2(128 char) not null,
        CRTDATE timestamp not null,
        NAME varchar2(64 char) not null,
        DESCRIPTION varchar2(256 char),
        primary key (ID)
    )TABLESPACE "@tables.tablespace@";
	

	create sequence @database.schema@.CAQUESTION_GEN;

	create index IDX_QUESTION_DELETED on @database.schema@.TB_CA_QUESTIONS (ISDELETED) TABLESPACE "@indexes.tablespace@";

    create index IDX_QUESTION_VERSION on @database.schema@.TB_CA_QUESTIONS (VERSION) TABLESPACE "@indexes.tablespace@";

	create index IDX_QUESTION_EVENT on @database.schema@.TB_CA_QUESTIONS (FKEVENT) TABLESPACE "@indexes.tablespace@";

	alter table @database.schema@.TB_CA_QUESTIONS 
        add constraint FK_QUESTIONEVENT
        foreign key (FKEVENT) 
        references @database.schema@.TB_CA_EVENTS;

    alter table @database.schema@.TB_CA_QUESTIONS 
        add constraint FK_ANSWERTYPE 
        foreign key (FKANSWERTYPE) 
        references @database.schema@.TB_CA_ANSWERTYPES;

	alter table @database.schema@.TB_SP_CUSTODIAN_ACCOUNTS 
        add constraint FK_PROVIDER 
        foreign key (FKPROVIDER) 
        references @database.schema@.TB_CA_EVENTPROVIDERS;

	create sequence @database.schema@.SPMANAGER_GEN;

	create sequence @database.schema@.SPMANAGERGROUP_GEN;

	create sequence @database.schema@.SPPROVIDERACCOUNT_GEN;

	create sequence @database.schema@.SPCUSTOMER_GEN;
	
	create sequence @database.schema@.SPCUSTOMERAUX_GEN;

	create table @database.schema@.AUX_TB_SP_CUSTOMER (
        ID number(19,0) not null,
        CUSTOMERID varchar2(16 char) not null,
        MANAGER varchar2(16 char) not null,
        AUDITORID varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        NAME varchar2(64 char) not null,
        STATE varchar2(1 char),
        VERSION number(10,0) not null,
        primary key (ID)
    )TABLESPACE "@tables.tablespace@";

	create table @database.schema@.AUX_TB_SP_MANAGER (
        ID number(19,0) not null,
        ISDELETED number(1,0) not null,
        CUSTOMERID varchar2(10 char) not null,
        EMAIL varchar2(320 char) not null,
        NAME varchar2(35 char) not null,
        VERSION number(10,0) not null,
		AUDITORID varchar2(128 char) not null,
        STATE varchar2(1 char),
        primary key (ID)
    )TABLESPACE "@tables.tablespace@";

	create sequence @database.schema@.SPEXCPORMAN_GEN;

 	create table @database.schema@.AUX_TB_SP_MARKETS (
        ID number(19,0) not null,
        mic varchar2(255 char),
        CITY varchar2(32 char) not null,
        MARKETCODE varchar2(64 char) not null,
        AUDITORID varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        NAME varchar2(128 char) not null,
        STATE varchar2(1 char),
        COUNTRY varchar2(2 char) not null,
        VERSION number(10,0) not null,
        primary key (ID)
    )TABLESPACE "@tables.tablespace@";

	create sequence @database.schema@.SPMARKETAUX_GEN;

	create table @database.schema@.AUX_TB_SP_SECURITIES (
		ID number(19,0) not null,
		CUSTOMERID varchar2(64 char),
		TICKER varchar2(32 char),
		EXPIRATIONDATE date,
		ISIN varchar2(12 char),
		CUSIP varchar2(9 char),
		SEDOL varchar2(7 char),
		IND_SECTOR varchar2(32 char),
		RADSUFKEY varchar2(10 char),
		VALUETYPE varchar2(39 char) not null,
		IND_GROUP varchar2(32 char),
		IND_SUBGROUP varchar2(32 char),
		REL_INDEX varchar2(8 char),
		AUDITORID varchar2(128 char) not null,
		ISDELETED number(1,0) not null,
		STATE varchar2(1 char),
		COUNTRY varchar2(2 char) not null,
		VERSION number(10,0) not null,
		DESCRIPTION varchar2(128 char),
		CURRENCY varchar2(3 char) not null,
		NAME varchar2(64 char) not null,
		MARKET varchar2(64 char) not null,
		primary key (ID)
	)TABLESPACE "@tables.tablespace@";

	create sequence @database.schema@.SPSECURITYAUX_GEN;

	create table @database.schema@.AUX_TB_SP_CUSTODIAN_ACCOUNTS (
        ID number(19,0) not null,
        customerId varchar2(255 char),
        AUDITORID varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        ACCOUNTNUMBER varchar2(64 char) not null,
        OWNACCOUNT number(1,0) not null,
        PROVIDERID varchar2(64 char) not null,
        NAME varchar2(64 char) not null,
        STATE varchar2(1 char),
        VERSION number(10,0) not null,
        primary key (ID)
    )TABLESPACE "@tables.tablespace@";

	create sequence @database.schema@.SPCUSTACCOUNT_GEN;

	create table @database.schema@.AUX_TB_SP_SECURITYPORTFOLIO (
        ID number(19,0) not null,
        AUDITORID varchar2(128 char) not null,
        ISDELETED number(1,0) not null,
        CUSTOMER varchar2(64 char) not null,
        CUSTOMERAMOUNT number(19,0) not null,
        EMISSIONDATE date not null,
        PROVIDERACCOUNT varchar2(64 char) not null,
        PORTFOLIO varchar2(64 char) not null,
        STATE varchar2(1 char),
        VERSION number(10,0) not null,
        primary key (ID)
    )TABLESPACE "@tables.tablespace@";

	create sequence @database.schema@.SPSECPORAUX_GEN;

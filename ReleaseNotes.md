**Version 1.0**
  * bug fixes - very very...;)
  * first version of parser for OSB 11g
  * first version (beta) of parser for SOA Suite 11g(Mediator, BPEL process, SCA, JCA)
  * new tool: show/find all headers in proxy service - OSB
  * new tool: show/find all variables in proxy service - OSB
  * new tool: show all partnerlinks in bpel process
  * new tool: show all veriables in bpel process
  * new tool: show/find all endpoint in selected project
  * display detail informations about selected activity in BPEL process or Proxy Service
  * new splash screen
  * new logo
  * new web site
  * new User Guide
**Version 0.8 - Technical preview/Beta 3**
  * bug fixes - very very much!!
  * display in the graph, the [endpoint](http://code.google.com/p/bpel-esb-dependency-analyzer/wiki/GettingStartedGuide_08_Beta3#Examples) for services


**Version 0.8 - Technical preview/Beta 2**
  * fixed errors in the parser and analyzer for the [Oracle Service Bus 10g](http://download.oracle.com/docs/cd/E13159_01/osb/docs10gr3/) ([Proxy](http://download.oracle.com/docs/cd/E13159_01/osb/docs10gr3/userguide/configuringandusingservices.html#wp1155541) and [Business](http://download.oracle.com/docs/cd/E13159_01/osb/docs10gr3/userguide/configuringandusingservices.html#wp1155387) service)
  * fixed errors in the parser and analyzer for the [Oracle SOA Suite 10g](http://en.wikipedia.org/wiki/Oracle_SOA_Suite) ([BPEL process](http://en.wikipedia.org/wiki/Business_Process_Execution_Language) and [ESB Services](http://en.wikipedia.org/wiki/Enterprise_service_bus))
  * parsing of dependencies from the exported jar files from OSB 10g
  * display dependencies between the BPEL processes and  the ESB Services
  * identified types of adapters for Oracle Service Bus 10g ([http](http://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol), [jms](http://en.wikipedia.org/wiki/Java_Message_Service), sb, dsp, [ejb](http://en.wikipedia.org/wiki/Enterprise_JavaBean), etc.)
  * identified types of adapters for Oracle ESB 10g(File, JMS, DB, AQ, FTP, MQ)
  * the new panel, which shows all services within the project
  * the new panel, which shows detailed information about selected service from Oracle Service Bus 10g
  * reworking the "open new workspace" wizard
  * delete the selected node in the visual graph
  * detailed tooltip about services and processes in all windows
  * displaying dependencies of Multi-window

**Version 0.8 - Technical preview/Beta 1**
  * is built as an Eclipse Rich Client Platform (RCP) for Windows, Linux and Mac OS X
  * for displaying dependencies between services/processes is used Eclipse GEF/ZEST framework
  * new parser and analyzer for BPEL processes from Oracle SOA Suite 10g
  * the first version of the parser and analyzer for Business and Proxy Services from Oracle Service Bus 10g
  * the first version of the parser and analyzer for BPEL processes from Open ESB

**Version 0.7**
  * bug-fix:*** reworking menu "File->Recent Files"
    * NullPointerException in Oracle BPEL 10g praser**

  * news:*** a simple visualizer(version 0.1) of dependencies between BPEL projects and ESB services and etc.
    * export visualizer graph to picture (now supports export to JPEG or PNG)
    * a simple changes in GUI**


**Version 0.6**
  * bug-fix:*** findUsage for ESB project does not show findUsage panel**

  * news:*** "Project Dependency" tree - new popup menu - contains new menu items:
> > > 'Find Usages for BPEL projects or ESB services'
    * "Project Structure" tree - new popup menu - contains new menu items:
> > > 'Find Usages for Variable or PartnerLink'**


**Version 0.5-b02**
  * bug-fix:*** bpel parser
    * workspace tree refresh
    * workspace/process tree selection
    * GUI
  * news:**
    * multi-workspace parser: analysis of several projects in several workspace
    * changes in the Oracel 10g BPEL parsers - parsing of new activities: email, voice, fax, sms, transformation, etc.
    * parsing the Oracle 10g BPEL projects that not included in Oracle 10g Jdeveloper workspace (**.jws) - but are in file system**


**Version 0.5-b01**
  * fix some bux in BPEL parser for Oracle SOA 10g
  * ESB(Enterprise Service Bus) parser for Oracle SOA 10g
  * analysis dependence between ESB projects
  * analysis dependence between BPEL and ESB projects
  * automatic detection workspace name for Oracle SOA 10g
  * utility for recently files open -  quick menu


Version 0.3
  * first stable version
  * analysis dependence between BPEL projects for Oracle SOA 10g

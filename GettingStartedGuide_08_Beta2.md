# What is SOA Dependency Analyzer #

SOA Dependency Analyzer is tool (utility) for graphically visualizing dependencies between the BPEL processes (WS - WSDL), ESB services (JMS, FTP, MQ, etc.), OSB services (Proxy and Business) and etc.
Its built on [Eclipse RCP (SWT) framework](http://wiki.eclipse.org/index.php/Rich_Client_Platform) and to visualize the dependency graph used Eclipse [GEF](http://www.eclipse.org/gef/)/[ZEST](http://www.eclipse.org/gef/zest/) framework.


# Getting Started with SOA Dependency Analyzer 0.8-Beta2 #

Requirements: Java 1.6

### What is the Multi-workspace and the Workspace? ###

Workspace may be either [Oracle SOA Suite 10g](http://www.oracle.com/technetwork/middleware/soasuite/downloads/downloads-085394.html#10g)
(contains [BPEL processes](http://en.wikipedia.org/wiki/Business_Process_Execution_Language) and [Enterprise Service Bus](http://en.wikipedia.org/wiki/Enterprise_service_bus) - ESB services) or
[Oracle Service Bus 10g - OSB](http://download.oracle.com/docs/cd/E13159_01/osb/docs10gr3/) (contains Proxy and Business services).
Depending on the type of workspace, the analyzer, will analyze the dependencies between projects.


![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/v0-8-beta2/Workspace.png](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/v0-8-beta2/Workspace.png)


### How to run the application? ###

**MAC OS X:**
.../soaDependencyAnalyzer/SoaDependencyAnalyzer.app

**WIN:**
.../soaDependencyAnalyzer/SoaDependencyAnalyzer.exe

**Linux:**
.../soaDependencyAnalyzer/SoaDependencyAnalyzer


### What's what ###

![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/v0-8-beta2/SoaDependencyAnalyzer08Desc.png](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/v0-8-beta2/SoaDependencyAnalyzer08Desc.png)

### Open New Multi-Workspace or Workspace Wizard ###

Click the left mouse button somewhere in the 'Workspaces View' and choose 'Open a new Workspace, or click the menu "File-> Open a new Workspace".

![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/v0-8-beta2/OpenNewWorkspaceWizard.png](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/v0-8-beta2/OpenNewWorkspaceWizard.png)


Allows you to select the type of the Mutli-workspace a Workspace:
| Type | Description |
|:-----|:------------|
|Oracle SOA Suite 10g (only from Workspace) | Analyzes the dependencies between the (BPEL Project - BPEL process and ESB Project -ESB Services.  Analyzer works only with projects in the workspace and not with jar(BPEL Process) or zip(ESB Services) exported from server. |
|Oracle Service Bus 10g | Analyzes the dependencies between the Proxy Services and Business Services |
|Open ESB | is demo |


Next page in the wizard lets you choose: either add a new the Workspace into a new the Mutli-Workspace or add a new Workspace in an existing the Mutli-Workspace.

![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/v0-8-beta2/OpenNewWorkspaceWizardSelectMultiWorkspace.png](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/v0-8-beta2/OpenNewWorkspaceWizardSelectMultiWorkspace.png)

Select an option and click Next.

This page allows you to add the paths to all workspaces, for that you want to analyze dependencies and visualize.

![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/v0-8-beta2/OpenNewWorkspaceWizardWorkspacePaths.png](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/v0-8-beta2/OpenNewWorkspaceWizardWorkspacePaths.png)

The last page it is really a summary page when you click the Finish button.



### Visual graph? ###

![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/v0-8-beta2/VisualGraph.png](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/v0-8-beta2/VisualGraph.png)

| Action in Graph | Description |
|:----------------|:------------|
| One click on the node (selection) | Show detailed overview of the selected node and him activities, which are dependent on other services, process, projects etc. |
| Double click on the node | Choosing a node in the graph and double click on him, to see all its dependencies (projects, services, processes etc.) |
| Right click - context menu | Display the context/popup menu with options such: zoom, change layout, export to image etc.|
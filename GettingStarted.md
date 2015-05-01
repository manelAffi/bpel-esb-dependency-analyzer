# Getting Started with bpel-esb-dependency-analyzer #

Requirements: Java 1.6


### How to open a Workspace or Multi Workspaces ###

bpel-esb-dependency-analyzer allows to open workspaces in two ways:

  1. Open one Workspace – analysis of dependencies between projects in one workspace.
    * File --> Open**> > ![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/frmOpen.gif](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/frmOpen.gif)
  1. Open Multi Workspace - analysis of dependencies between projects in several workspaces
    * File --> Open Multi-Workspace**
> > ![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/frmOpenMulti.gif](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/frmOpenMulti.gif)




### Analysis of dependencies between BPEL and ESB projects ###


> "Project Dependencies"**shows the dependencies between BPEL and ESB projects
> > ![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/projectDependencies.gif](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/projectDependencies.gif)

> "Depending on activity-based operations" shows the dependencies on the BPEL processes or ESB services that are called from activities(receive, invoke, etc.) that contain WSLD operations
> > ![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/activityOperationDependnecies.gif](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/activityOperationDependnecies.gif)

> "Project Structure" shows all activities in BPEL process
> > ![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/projectStructure.gif](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/projectStructure.gif)

> "Esb Services" shows all systems, groups, etc. This tree shows dependencies to WSDL operations
> > ![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/esbServiceTree.gif](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/esbServiceTree.gif)**

### Find Usages... ###


> The first two trees containing the popup menu wich contains two items, **_Find Usage for BPEL project_** and **_Find Usage for ESB project_**
> > ![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/findUsageForProjects.gif](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/findUsageForProjects.gif)

> "Project Structure" tree containing the popup menu wich contains two items, **_Find Usage for Partner Link_** and **_Find Usage for Variable_**
> > ![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/findUsageForPartnerLink.gif](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/findUsageForPartnerLink.gif)
> > ![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/findUsageForVariables.gif](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/findUsageForVariables.gif)
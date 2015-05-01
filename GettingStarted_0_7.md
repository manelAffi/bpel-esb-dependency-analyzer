# Getting Started with bpel-esb-dependency-analyzer #

Requirements: Java 1.6


### How to open a Workspace or Multi Workspaces ###

> bpel-esb-dependency-analyzer allows to open workspaces in two ways:

  1. Open one Workspace – analysis of dependencies between projects in one workspace.
    * File --> Open**> > ![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/frmOpen.gif](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/frmOpen.gif)
  1. Open Multi Workspace - analysis of dependencies between projects in several workspaces
    * File --> Open Multi-Workspace**
> > ![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/frmOpenMulti.gif](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/frmOpenMulti.gif)



### A visual graph of dependencies between BPEL and ESB projects ###


> ![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/v0-7/graf01.gif](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/v0-7/graf01.gif)




### Analysis of dependencies between BPEL and ESB projects ###

> "Project Dependencies"**shows the dependencies between BPEL and ESB projects
> > ![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/v0-7/projectDependencies.gif](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/v0-7/projectDependencies.gif)

> "Depending on activity-based operations" shows the dependencies on the BPEL processes or ESB services that are called from activities(receive, invoke, etc.) that contain WSLD operations
> > ![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/activityOperationDependnecies.gif](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/activityOperationDependnecies.gif)

> "Project Structure" shows all activities in BPEL process
> > ![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/projectStructure.gif](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/projectStructure.gif)

> "Esb Services" shows all systems, groups, etc. This tree shows dependencies to WSDL operations
> > ![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/esbServiceTree.gif](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/esbServiceTree.gif)**


### Find Usages... ###


> The first tree contains the popup menu wich contains two items for "find.." utilities: **_Find Usage for BPEL project_**, **_Find Usage for ESB project_**
> > ![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/findUsageForProjects.gif](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/findUsageForProjects.gif)

> "Project Structure" tree containing the popup menu wich contains two items, **_Find Usage for Partner Link_** and **_Find Usage for Variable_**
> > ![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/findUsageForPartnerLink.gif](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/findUsageForPartnerLink.gif)
> > ![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/findUsageForVariables.gif](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/findUsageForVariables.gif)


### Unknown projects or services... ###


> If you are selected an unknown project in tree of "Project Dependecies" then shows **"Unknown project panel"**, which displays basic information about the unknown service and list of activities wich uses by an unknown service.
> > ![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/v0-7/unknownProjectPanel.gif](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/v0-7/unknownProjectPanel.gif)



### Project Info... ###


> The first tree contains a popup menu that contains the item **Properties**.
> > ![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/v0-7/aboutProject.gif](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/v0-7/aboutProject.gif)

> Form "Properties, or About project", contains basic information about the project and all operations under this project.


### Export dependency graph... ###

> The new version includes features to export the dependency graph to image. Form for export is in **Visual -> Export**
> > ![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/v0-7/menu_export.gif](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/v0-7/menu_export.gif)

> Dependency Graph you can export to png or jpg. For a successful export, it is necessary to add the path to the file, which will be made to export and file name must have extension **png** or **jpg**, for example: _c:\myExport.png_ or _c:\myExport.jpg_
> > ![http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/v0-7/frm_export.gif](http://bpel-esb-dependency-analyzer.googlecode.com/svn/wiki/GettingStarted/v0-7/frm_export.gif)
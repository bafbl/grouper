<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--
  $Id: personview-print.jsp,v 1.3 2006-10-25 00:13:31 ddonn Exp $
  $Date: 2006-10-25 00:13:31 $
  
  Copyright 2004 Internet2 and Stanford University.  All Rights Reserved.
  Licensed under the Signet License, Version 1,
  see doc/license.txt in this distribution.
-->

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <meta name="robots" content="noindex, nofollow" />
    <title>
      <%=ResLoaderUI.getString("signet.title") %>
    </title>
    <link href="styles/signet.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" type="text/javascript" src="scripts/signet.js"></script>
  </head>

  <body>
  
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<%@ page import="java.text.DateFormat" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.SortedSet" %>
<%@ page import="java.util.TreeSet" %>
<%@ page import="java.util.Date" %>

<%@ page import="edu.internet2.middleware.signet.Signet" %>
<%@ page import="edu.internet2.middleware.signet.subjsrc.SignetSubject" %>
<%@ page import="edu.internet2.middleware.signet.Subsystem" %>
<%@ page import="edu.internet2.middleware.signet.Category" %>
<%@ page import="edu.internet2.middleware.signet.Grantable" %>
<%@ page import="edu.internet2.middleware.signet.Assignment" %>
<%@ page import="edu.internet2.middleware.signet.Function" %>
<%@ page import="edu.internet2.middleware.signet.Status" %>
<%@ page import="edu.internet2.middleware.signet.Proxy" %>

<%@ page import="edu.internet2.middleware.signet.resource.ResLoaderUI" %>

<%@ page import="edu.internet2.middleware.signet.ui.Common" %>
<%@ page import="edu.internet2.middleware.signet.ui.Constants" %>
<%@ page import="edu.internet2.middleware.signet.ui.PrivDisplayType" %>

<% 
  Signet signet
     = (Signet)
         (request.getSession().getAttribute("signet"));
         
  SignetSubject loggedInPrivilegedSubject
    = (SignetSubject)
        (request.getSession().getAttribute(Constants.LOGGEDINUSER_ATTRNAME));
   
  SignetSubject pSubject
    = (SignetSubject)
        (request.getSession().getAttribute(Constants.CURRENTPSUBJECT_ATTRNAME));
         
  Subsystem currentSubsystem
    = (Subsystem)
        (request.getSession().getAttribute(Constants.SUBSYSTEM_ATTRNAME));
         
  PrivDisplayType privDisplayType
    = (PrivDisplayType)
        (request.getSession().getAttribute(Constants.PRIVDISPLAYTYPE_ATTRNAME));
         
  DateFormat dateFormat = DateFormat.getDateInstance();
%>

  	<!-- removing header to use full page for print view; form is not required 
    <form action="" method="post" name="form1" id="form1">
      <tiles:insert page="/tiles/header.jsp" flush="true" />
      <div id="Layout"> 
	 --> 
        <h1>
          <%=Common.titleForPrintReport
          		(currentSubsystem,
          		 privDisplayType,
          		 pSubject)%>
        </h1>
		  <p class="dropback"><%=ResLoaderUI.getString("personview-print.date.txt")%><%=Common.displayDatetime(Constants.DATETIME_FORMAT_24_SECOND, new Date())%></p> 
      <a href="PersonView.do"><img src="images/arrow_left.gif" alt="" /><%=ResLoaderUI.getString("personview-print.return.txt") %></a>

        
          <table>
            <tr>
<%
  if (privDisplayType.equals(PrivDisplayType.CURRENT_GRANTED)
      || privDisplayType.equals(PrivDisplayType.FORMER_GRANTED))
  {
%>
              <td><b><%=ResLoaderUI.getString("privilegesGrantedReport.subject.hdr")%></b></td>
<%
  }
%>
              <td><b><%=ResLoaderUI.getString("privilegesGrantedReport.privilege.hdr")%></b></td>
              <td><b><%=ResLoaderUI.getString("privilegesGrantedReport.scope.hdr")%></b></td>
              <td><b><%=ResLoaderUI.getString("privilegesGrantedReport.limits.hdr")%></b></td>
              <td><b><%=ResLoaderUI.getString("privilegesGrantedReport.status.hdr")%></b></td>
            </tr>
<%
  if (currentSubsystem != null)
  {
    Subsystem subsystemFilter = null;
  
    if (!currentSubsystem.equals(Constants.WILDCARD_SUBSYSTEM))
    {
      subsystemFilter = currentSubsystem;
    }
  
    SortedSet assignmentsAndProxies;

    assignmentsAndProxies
      = Common.getGrantablesForReport
          (pSubject, subsystemFilter, privDisplayType);
             
    Iterator assignmentsAndProxiesIterator = assignmentsAndProxies.iterator();
    while (assignmentsAndProxiesIterator.hasNext())
    {
      Grantable grantable = (Grantable)(assignmentsAndProxiesIterator.next());
%>
  
            <tr>
<%
      if (privDisplayType.equals(PrivDisplayType.CURRENT_GRANTED)
          || privDisplayType.equals(PrivDisplayType.FORMER_GRANTED))
      {
%>
              <td>
                <%=grantable.getGrantee().getName()%>
              </td>
<%
      }
%>
              <td>
                <%=Common.privilegeStr(signet, grantable)%>
              </td>
              <td>
                <%=Common.scopeStr(grantable)%>
              </td>
              <td> <!-- limits -->
                <%=Common.displayLimitValues(grantable)%>
              </td> <!-- limits -->
              <td> <!-- status -->
                <%=Common.displayStatus(grantable)%>
              </td> <!-- status -->
            </tr>
<%
    }
  }
%>
            
        </table>
	<!-- removing footer and end of layout div	
        <tiles:insert page="/tiles/footer.jsp" flush="true" />
      </div> 
    </form>
	-->
  </body>
</html>

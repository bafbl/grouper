/*--
$Id: AdapterUnavailableException.java,v 1.2 2006-02-09 10:17:32 lmcrae Exp $
$Date: 2006-02-09 10:17:32 $

Copyright 2006 Internet2, Stanford University

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package edu.internet2.middleware.signet;

/**
Used to indicate that the adapter is not available.
*/
public class AdapterUnavailableException extends Exception
{
public AdapterUnavailableException(String msg)
{
	super(msg);
}

public AdapterUnavailableException(String msg, Throwable cause)
{
	super(msg, cause);
}
}

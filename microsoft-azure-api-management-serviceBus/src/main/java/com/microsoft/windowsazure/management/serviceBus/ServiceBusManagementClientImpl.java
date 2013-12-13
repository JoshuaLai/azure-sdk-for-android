// 
// Copyright (c) Microsoft and contributors.  All rights reserved.
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//   http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// 
// See the License for the specific language governing permissions and
// limitations under the License.
// 

// Warning: This code was generated by a tool.
// 
// Changes to this file may cause incorrect behavior and will be lost if the
// code is regenerated.

package com.microsoft.windowsazure.management.servicebus;

import com.microsoft.windowsazure.management.ManagementConfiguration;
import com.microsoft.windowsazure.management.SubscriptionCloudCredentials;
import com.microsoft.windowsazure.management.servicebus.models.OperationStatus;
import com.microsoft.windowsazure.management.servicebus.models.ServiceBusLocation;
import com.microsoft.windowsazure.management.servicebus.models.ServiceBusOperationStatusResponse;
import com.microsoft.windowsazure.management.servicebus.models.ServiceBusRegionsResponse;
import com.microsoft.windowsazure.services.core.ServiceClient;
import com.microsoft.windowsazure.services.core.ServiceException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
* The Service Bus Management API is a REST API for managing Service Bus queues,
* topics, rules and subscriptions.  (see
* http://msdn.microsoft.com/en-us/library/windowsazure/hh780776.aspx for more
* information)
*/
public class ServiceBusManagementClientImpl extends ServiceClient<ServiceBusManagementClientImpl> implements ServiceBusManagementClient
{
    private URI baseUri;
    
    /**
    * The URI used as the base for all Service Bus requests.
    */
    public URI getBaseUri() { return this.baseUri; }
    
    private SubscriptionCloudCredentials credentials;
    
    /**
    * When you create a Windows Azure subscription, it is uniquely identified
    * by a subscription ID. The subscription ID forms part of the URI for
    * every call that you make to the Service Management API.  The Windows
    * Azure Service ManagementAPI use mutual authentication of management
    * certificates over SSL to ensure that a request made to the service is
    * secure.  No anonymous requests are allowed.
    */
    public SubscriptionCloudCredentials getCredentials() { return this.credentials; }
    
    private NamespaceOperations namespaces;
    
    /**
    * The Service Bus Management API includes operations for managing Service
    * Bus namespaces.
    */
    public NamespaceOperations getNamespaces() { return this.namespaces; }
    
    private NotificationHubOperations notificationHubs;
    
    /**
    * The Service Bus Management API includes operations for managing Service
    * Bus queues.
    */
    public NotificationHubOperations getNotificationHubs() { return this.notificationHubs; }
    
    private QueueOperations queues;
    
    /**
    * The Service Bus Management API includes operations for managing Service
    * Bus queues.
    */
    public QueueOperations getQueues() { return this.queues; }
    
    private RelayOperations relays;
    
    /**
    * The Service Bus Management API includes operations for managing Service
    * Bus relays.
    */
    public RelayOperations getRelays() { return this.relays; }
    
    private TopicOperations topics;
    
    /**
    * The Service Bus Management API includes operations for managing Service
    * Bus topics for a namespace.
    */
    public TopicOperations getTopics() { return this.topics; }
    
    /**
    * Initializes a new instance of the ServiceBusManagementClientImpl class.
    *
    */
    private ServiceBusManagementClientImpl()
    {
        super();
        this.namespaces = new NamespaceOperationsImpl(this);
        this.notificationHubs = new NotificationHubOperationsImpl(this);
        this.queues = new QueueOperationsImpl(this);
        this.relays = new RelayOperationsImpl(this);
        this.topics = new TopicOperationsImpl(this);
    }
    
    /**
    * Initializes a new instance of the ServiceBusManagementClientImpl class.
    *
    * @param credentials When you create a Windows Azure subscription, it is
    * uniquely identified by a subscription ID. The subscription ID forms part
    * of the URI for every call that you make to the Service Management API.
    * The Windows Azure Service ManagementAPI use mutual authentication of
    * management certificates over SSL to ensure that a request made to the
    * service is secure.  No anonymous requests are allowed.
    * @param baseUri The URI used as the base for all Service Bus requests.
    */
    public ServiceBusManagementClientImpl(SubscriptionCloudCredentials credentials, URI baseUri)
    {
        this();
        if (credentials == null)
        {
            throw new NullPointerException("credentials");
        }
        if (baseUri == null)
        {
            throw new NullPointerException("baseUri");
        }
        this.credentials = credentials;
        this.baseUri = baseUri;
        
        httpClient = credentials.initializeClient();
    }
    
    /**
    * Initializes a new instance of the ServiceBusManagementClientImpl class.
    *
    * @param credentials When you create a Windows Azure subscription, it is
    * uniquely identified by a subscription ID. The subscription ID forms part
    * of the URI for every call that you make to the Service Management API.
    * The Windows Azure Service ManagementAPI use mutual authentication of
    * management certificates over SSL to ensure that a request made to the
    * service is secure.  No anonymous requests are allowed.
    */
    @Inject
    public ServiceBusManagementClientImpl(@Named(ManagementConfiguration.SUBSCRIPTION_CLOUD_CREDENTIALS) SubscriptionCloudCredentials credentials) throws java.net.URISyntaxException
    {
        this();
        if (credentials == null)
        {
            throw new NullPointerException("credentials");
        }
        this.credentials = credentials;
        this.baseUri = new URI("https://management.core.windows.net");
        
        httpClient = credentials.initializeClient();
    }
    
    /**
    * The Get Operation Status operation returns the status of thespecified
    * operation. After calling an asynchronous operation, you can call Get
    * Operation Status to determine whether the operation has succeeded,
    * failed, or is still in progress.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/ee460783.aspx for
    * more information)
    *
    * @param requestId The request ID for the request you wish to track. The
    * request ID is returned in the x-ms-request-id response header for every
    * request.
    * @return The response body contains the status of the specified
    * asynchronous operation, indicating whether it has succeeded, is
    * inprogress, or has failed. Note that this status is distinct from the
    * HTTP status code returned for the Get Operation Status operation itself.
    * If the asynchronous operation succeeded, the response body includes the
    * HTTP status code for the successful request.  If the asynchronous
    * operation failed, the response body includes the HTTP status code for
    * the failed request, and also includes error information regarding the
    * failure.
    */
    @Override
    public Future<ServiceBusOperationStatusResponse> getOperationStatusAsync(final String requestId)
    {
        return this.getExecutorService().submit(new Callable<ServiceBusOperationStatusResponse>() { @Override
        public ServiceBusOperationStatusResponse call() throws Exception
        {
            return getOperationStatus(requestId);
        }
         });
    }
    
    /**
    * The Get Operation Status operation returns the status of thespecified
    * operation. After calling an asynchronous operation, you can call Get
    * Operation Status to determine whether the operation has succeeded,
    * failed, or is still in progress.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/ee460783.aspx for
    * more information)
    *
    * @param requestId The request ID for the request you wish to track. The
    * request ID is returned in the x-ms-request-id response header for every
    * request.
    * @return The response body contains the status of the specified
    * asynchronous operation, indicating whether it has succeeded, is
    * inprogress, or has failed. Note that this status is distinct from the
    * HTTP status code returned for the Get Operation Status operation itself.
    * If the asynchronous operation succeeded, the response body includes the
    * HTTP status code for the successful request.  If the asynchronous
    * operation failed, the response body includes the HTTP status code for
    * the failed request, and also includes error information regarding the
    * failure.
    */
    @Override
    public ServiceBusOperationStatusResponse getOperationStatus(String requestId) throws IOException, ServiceException, ParserConfigurationException, SAXException
    {
        // Validate
        if (requestId == null)
        {
            throw new NullPointerException("requestId");
        }
        
        // Tracing
        
        // Construct URL
        String url = this.getBaseUri() + "/" + this.getCredentials().getSubscriptionId() + "/operations/" + requestId;
        
        // Create HTTP transport objects
        HttpGet httpRequest = new HttpGet(url);
        
        // Set Headers
        httpRequest.setHeader("x-ms-version", "2013-08-01");
        
        // Send Request
        HttpResponse httpResponse = null;
        httpResponse = this.getHttpClient().execute(httpRequest);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != 200)
        {
            ServiceException ex = ServiceException.createFromXml(httpRequest, null, httpResponse, httpResponse.getEntity());
            throw ex;
        }
        
        // Create Result
        ServiceBusOperationStatusResponse result = null;
        // Deserialize Response
        InputStream responseContent = httpResponse.getEntity().getContent();
        result = new ServiceBusOperationStatusResponse();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document responseDoc = documentBuilder.parse(responseContent);
        
        NodeList elements = responseDoc.getElementsByTagName("Operation");
        Element operationElement = elements.getLength() > 0 ? ((Element)elements.item(0)) : null;
        if (operationElement != null)
        {
            NodeList elements2 = operationElement.getElementsByTagName("ID");
            Element idElement = elements2.getLength() > 0 ? ((Element)elements2.item(0)) : null;
            if (idElement != null)
            {
                String idInstance;
                idInstance = idElement.getTextContent();
                result.setId(idInstance);
            }
            
            NodeList elements3 = operationElement.getElementsByTagName("Status");
            Element statusElement = elements3.getLength() > 0 ? ((Element)elements3.item(0)) : null;
            if (statusElement != null)
            {
                OperationStatus statusInstance;
                statusInstance = OperationStatus.valueOf(statusElement.getTextContent());
                result.setStatus(statusInstance);
            }
            
            NodeList elements4 = operationElement.getElementsByTagName("HttpStatusCode");
            Element httpStatusCodeElement = elements4.getLength() > 0 ? ((Element)elements4.item(0)) : null;
            if (httpStatusCodeElement != null)
            {
                Integer httpStatusCodeInstance;
                httpStatusCodeInstance = Integer.valueOf(httpStatusCodeElement.getTextContent());
                result.setHttpStatusCode(httpStatusCodeInstance);
            }
            
            NodeList elements5 = operationElement.getElementsByTagName("Error");
            Element errorElement = elements5.getLength() > 0 ? ((Element)elements5.item(0)) : null;
            if (errorElement != null)
            {
                ServiceBusOperationStatusResponse.ErrorDetails errorInstance = new ServiceBusOperationStatusResponse.ErrorDetails();
                result.setError(errorInstance);
                
                NodeList elements6 = errorElement.getElementsByTagName("Code");
                Element codeElement = elements6.getLength() > 0 ? ((Element)elements6.item(0)) : null;
                if (codeElement != null)
                {
                    String codeInstance;
                    codeInstance = codeElement.getTextContent();
                    errorInstance.setCode(codeInstance);
                }
                
                NodeList elements7 = errorElement.getElementsByTagName("Message");
                Element messageElement = elements7.getLength() > 0 ? ((Element)elements7.item(0)) : null;
                if (messageElement != null)
                {
                    String messageInstance;
                    messageInstance = messageElement.getTextContent();
                    errorInstance.setMessage(messageInstance);
                }
            }
        }
        
        result.setStatusCode(statusCode);
        if (httpResponse.getHeaders("x-ms-request-id").length > 0)
        {
            result.setRequestId(httpResponse.getFirstHeader("x-ms-request-id").getValue());
        }
        
        return result;
    }
    
    /**
    * Retrieves the list of regions that support the creation and management of
    * Service Bus service namespaces.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/jj860465.aspx for
    * more information)
    *
    * @return A response to a request for a list of regions.
    */
    @Override
    public Future<ServiceBusRegionsResponse> getServiceBusRegionsAsync()
    {
        return this.getExecutorService().submit(new Callable<ServiceBusRegionsResponse>() { @Override
        public ServiceBusRegionsResponse call() throws Exception
        {
            return getServiceBusRegions();
        }
         });
    }
    
    /**
    * Retrieves the list of regions that support the creation and management of
    * Service Bus service namespaces.  (see
    * http://msdn.microsoft.com/en-us/library/windowsazure/jj860465.aspx for
    * more information)
    *
    * @return A response to a request for a list of regions.
    */
    @Override
    public ServiceBusRegionsResponse getServiceBusRegions() throws IOException, ServiceException, ParserConfigurationException, SAXException
    {
        // Validate
        
        // Tracing
        
        // Construct URL
        String url = this.getBaseUri() + "/" + this.getCredentials().getSubscriptionId() + "/services/servicebus/regions";
        
        // Create HTTP transport objects
        HttpGet httpRequest = new HttpGet(url);
        
        // Set Headers
        httpRequest.setHeader("Accept", "application/atom+xml");
        httpRequest.setHeader("Content-Type", "application/atom+xml");
        httpRequest.setHeader("x-ms-version", "2013-08-01");
        
        // Send Request
        HttpResponse httpResponse = null;
        httpResponse = this.getHttpClient().execute(httpRequest);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != 200)
        {
            ServiceException ex = ServiceException.createFromXml(httpRequest, null, httpResponse, httpResponse.getEntity());
            throw ex;
        }
        
        // Create Result
        ServiceBusRegionsResponse result = null;
        // Deserialize Response
        InputStream responseContent = httpResponse.getEntity().getContent();
        result = new ServiceBusRegionsResponse();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document responseDoc = documentBuilder.parse(responseContent);
        
        NodeList elements = responseDoc.getElementsByTagNameNS("http://www.w3.org/2005/Atom", "feed");
        Element feedElement = elements.getLength() > 0 ? ((Element)elements.item(0)) : null;
        if (feedElement != null)
        {
            if (feedElement != null)
            {
                for (int i1 = 0; i1 < feedElement.getElementsByTagNameNS("http://www.w3.org/2005/Atom", "entry").getLength(); i1 = i1 + 1)
                {
                    org.w3c.dom.Element entriesElement = ((org.w3c.dom.Element)feedElement.getElementsByTagNameNS("http://www.w3.org/2005/Atom", "entry").item(i1));
                    ServiceBusLocation entryInstance = new ServiceBusLocation();
                    result.getRegions().add(entryInstance);
                    
                    NodeList elements2 = entriesElement.getElementsByTagNameNS("http://www.w3.org/2005/Atom", "content");
                    Element contentElement = elements2.getLength() > 0 ? ((Element)elements2.item(0)) : null;
                    if (contentElement != null)
                    {
                        NodeList elements3 = contentElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "RegionCodeDescription");
                        Element regionCodeDescriptionElement = elements3.getLength() > 0 ? ((Element)elements3.item(0)) : null;
                        if (regionCodeDescriptionElement != null)
                        {
                            NodeList elements4 = regionCodeDescriptionElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "Code");
                            Element codeElement = elements4.getLength() > 0 ? ((Element)elements4.item(0)) : null;
                            if (codeElement != null)
                            {
                                String codeInstance;
                                codeInstance = codeElement.getTextContent();
                                entryInstance.setCode(codeInstance);
                            }
                            
                            NodeList elements5 = regionCodeDescriptionElement.getElementsByTagNameNS("http://schemas.microsoft.com/netservices/2010/10/servicebus/connect", "FullName");
                            Element fullNameElement = elements5.getLength() > 0 ? ((Element)elements5.item(0)) : null;
                            if (fullNameElement != null)
                            {
                                String fullNameInstance;
                                fullNameInstance = fullNameElement.getTextContent();
                                entryInstance.setFullName(fullNameInstance);
                            }
                        }
                    }
                }
            }
        }
        
        result.setStatusCode(statusCode);
        if (httpResponse.getHeaders("x-ms-request-id").length > 0)
        {
            result.setRequestId(httpResponse.getFirstHeader("x-ms-request-id").getValue());
        }
        
        return result;
    }
}

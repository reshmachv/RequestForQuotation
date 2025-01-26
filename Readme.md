# Microservices-based RFQ and Supplier Quotation System
##### Overview
This project is built using a microservices architecture to handle a system where customers can create Request for Quotations (RFQ), add products to a cart, and suppliers can quote against those RFQs.

##### System Components
There are 5 primary microservices involved in this system:

###### Customer Service: Manages customer registration, login, and RFQ creation.
###### Product Service: Handles product listings and details.
###### Cart Service: Manages the customer cart, adding/removing products, and linking products to an RFQ.
###### RFQ Service: Facilitates RFQ creation, status updates, and links customer carts with RFQs.
###### Supplier Service: Allows suppliers to view RFQs and submit quotations.
Each of these services communicates with each other via REST APIs to ensure that customer requests are processed and suppliers can respond to RFQs efficiently.

###### Features

Customer can:
Create an RFQ by selecting products.
Add products to the cart and generate an RFQ.
View RFQs and associated supplier quotations.

Supplier can:
View available RFQs.
Submit quotations for the RFQs.
View responses and quote history.

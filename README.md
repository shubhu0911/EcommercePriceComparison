Requirements:
1. Implement a Spring Boot application with Web Client to perform concurrent API 
calls.
2. Configure the application to fetch product data from multiple e-commerce APIs (e.g., 
Amazon Backend, eBay Backend, Walmart Backend, etc.).
3. Fetch product information, prices, availability, and other relevant data from each API 
concurrently.
4. Retrieve the product data in JSON format from each API response.
5. Aggregate the product data from all the APIs into a single response.
6. Compare prices and identify the best deals for each category.
7. Implement error handling and graceful degradation for failed API calls.
8. Display or provide the aggregated product data and price comparisons to the users.
9. Utilize Java 8 features such as lambdas, streams, and Completable Future for 
concurrent processing.

PART 1:
1. Create three separate components in Spring Boot, each representing a different backend server.
2. Utilize JPA Repositories within each component to retrieve hard-coded responses.

PART 2:
• Initiate concurrent requests to fetch transactions from each backend server using 
Completable Future.
• Collect the responses and combine them into a single list using Stream APIs.
• Put the logic to find out the best deals based on discount, price, stock availability, current 
date.
• Filter & Sort the List Items by Maximum Discount at the top, if two Items have similar 
discount then compare the prices, Lower price should be stay at the top.
• If stock is zero, we must skip the item from the response.
• we must skip the item from the response if the current date does not lie between the deal 
start date and deal end date.

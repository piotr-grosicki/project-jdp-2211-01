1. Ecommerce is a  REST online store project, which is using mySQL as a database source, build with Spring and Hibernate frameworks.
2. <screens>,
3. To compile project, we need JVM version 1.8 with SpringBoot framework version 2.1.18.RELEASE
4. To build project we have to use command ’ ./gradlew build ’
5. Endpoints:
   1. Product Controller:
   Product controller has the following endpoints:

Get an Action

GET /v1/products -  return list of all products

GET /v1/products/{productId} - display the product details

productId REQUIRED path parameters
Long

DELETE /v1/products/{productId} - delete the product by product id

productId REQUIRED path parameters
Long

POST /v1/products - adding new product, request body JSON format required

Example :
{
„Name”: „name”
„Description”: „description”
„Quantity’: 223,
„Price”: 100
}

PUT /v1/products - editing the existing product, request body JSON format required

Example :
{
"id": 1,
"Name": "name:
"Description": "Description"
"description": 223,
"Price": 100
}

6. You can use the project as a background for online store. To develop the architecture of application, we can add tracking tools to collect data about our users and prepare personalized offers for out customers.

7.


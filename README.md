Assumptions: 
1. Flight price will depend on factors like popular holidays, Weekends,
So, therefore, some rules have been added. Each flight will have base price but
actual price will either reduce or increase depending on business rules.

2.I didn't mock response of the downstream system rather I called different end-points of one of the public API.

3. I used reactive mongo db for storing data.
4. Please use default port for mongo db.
5. Wrote some test cases mainly for controller (resource) and service.
6. I didn't add any BRMS (Rules) engine for price calculation rather I used only one
java file for rule calculations.
7. Changes could have been made better way but due to my busy schedule and ambiguity of requirements I could not able to worte enough test cases and enough business rules.

GET call:

For flight information:  http://localhost:8082/flight?date=2021-11-22&arrCode=DXB&depCode=MAA

For Price: http://localhost:8082/price?flightNo=BA4123&flightDate=2021-11-23

POST Call:

To save flight details:

http://localhost:8082/flight

Body:
{
"flightNumber": "EA1165",
"flightDate": "2021-11-30",
"basePrice": 7000.0,
"depCode": "DXB",
"arrCode": "CAP"
}

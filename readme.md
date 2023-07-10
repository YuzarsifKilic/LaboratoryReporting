# Laboratory Reporting

## Spring Boot And React Application

### Summary
This projects show reports, update reports and delete reports. My endpoints are protected with jwt tokens. My tokens expire 30 minutes

### Requirements
* Laborants can have n reports but a report can only have one laborant
* Normal user just add a report and update
* Admin adds, updates and deletes report
* Reports can be seached by patient name and surname
* Reports can be searched by laborant name and surname
* Login to the system with username and password

---
The application has 4 apis

* LaborantAPI
* PatientAPI
* ReportAPI
* UserAPI

```
GET /api/v1/laborant/getall - retrieves all laborants

GET /api/v1/patients/getall - retrieves all patients

POST /api/v1/report/create - create a new report
GET /api/v1/report/getReport/{id} - retrieves a report by report id
GET /api/v1/report/getall - retrieves all reprots
PUT /api/v1/report/{id} - updates a report by id
DELETE /api/v1/report/{id} - deletes a report by id (just for admins)
POST /api/v1/report/getByPatient - retrieves reports by patient name and surname
POST /api/v1/report/getByLaborant - retrieves reports by laborant name and surname
POST /api/v1/report/getByDate - retrieves reports sorted by date
POST /api/v1/report/saveImage/{id} - save the photo of the report by id
POST /api/v1/report/getImage/{id} - retrieve the photo of the report by id

POST /api/v1/user/login -  checking username and password to login site
```

## Run & Build
$PORT:8080

```
cd laboratory_reporting/api/LaboratoryReporting
mvn clean install
mvn spring-boot:run

cd laboratory_reporting/frontend/laboratory_reporting
npm install 
npm run
```

### Username And Password For Login

* username: yuzarsif password : yuzarsif (normal user)
* username : admin password : admin (admin)

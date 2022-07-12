# *Medical data analyzer*

---
### *Description*
This web-app has convenient and intuitive interface. 
App helps to storage and analyze incoming medical data (analyzes) and gets back result of them. 
Moreover, it helps and doctor to control patients health parameters and warning them if it needs.
***

### *Used tools*

***
Technologies:
*Spring Boot, Spring Security, Hibernate, Thymeleaf, JQuery, Bootstrap, Ajax, Fetch API, Mockito, Junit, Slf4j,
Lombok, Jackson, Flyway, html/css/js.*

### *Database schema*
![img.png](rdme/img.png)
---

### *Use cases (Possibilities)*

---
#### General:
- authorization
- i18n (RU, EN);
- pagination
- password recover (via email)
#### Admin:
- registration (via email)
- registration of surveillance services
- formation of the history of analyzes
- formation of biochemical analyzes
- formation of general analyzes
- analysis of medical indicators
- filling out analyzes
- getting doctor's advice
- profiling
- google captcha
- correction of information
#### User:
- block/unblock users
- change of access rights
- adjusting user information
- creating an authorization report
- screenshot generation
- statistic of app usage 
- upload profile photo

#### Doctor:
- making recommendations to the patient
- monitoring of medical indicators

### *Some screenshots*
![img.png](rdme/img11.png)
![img_2.png](rdme/img_2.png)
![img_3.png](rdme/img_3.png)
![img_4.png](rdme/img_4.png)
![img_5.png](rdme/img_5.png)
![img_6.png](rdme/img_6.png)
![img_7.png](rdme/img_7.png)
![img_8.png](rdme/img_8.png)
![img_9.png](rdme/img_9.png)
![img_10.png](rdme/img_10.png)
![img_11.png](rdme/img_11.png)
---

### *Development log*

---
    Developed abstract dao, connection pool, services, login/main pages and etc...
    before...

    21/02/2022 - i18n implementation
    22/02/2022 - Replaced c3p0 on HikariCP
    23/02/2022 - Thymeleaf implementation
    02/03/2022 - Design login and registration pages, abstract dao, services 
    04/03/2022 - Redesign pages, added controllers
    07/03/2022 - Form validation (front-end)
    08/03/2022 - Form validation (back-end)
    08/03/2022 - Developed personal, edit data pages; logs
    12/03/2022 - User managment page
    13/03/2022 - Injected jquery, replaced synchronic request on asynch via ajax
    14/03/2022 - Implemeted activity chart via highcharts.com
    17/03/2022 - Form validation (back/front-end)
    19/03/2022 - Analyzes page
    22/03/2022 - Pagination, replace Ajax on Fetch API
    25/03/2022 - Addded i18n data, deleted useless elements
---

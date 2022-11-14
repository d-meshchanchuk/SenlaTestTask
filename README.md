#ATM simulation

###Functionality

- Card authorization;
- Check balance
- Get money
- Top up  balance

####Repository format
```
/data/repository.txt

XXXX-XXXX-XXXX-XXXX password balance countFailAuthorization timeBlock

Example: 
2222-1111-1111-1111 1234 10000 0 2022-11-13T16:31:34.832529222 

```

## Quick start

### Required:

- Java 11
- Maven
- Docker

### Steps:

```
- git clone https://github.com/d-meshchanchuk/SenlaTestTask.git
- cd SenlaTestTask
- mvn clean package
    - sh script.sh 
    OR
    - docker build -t atm_app .
    - docker run -it --mount type=bind,source=ABSOLUTE_PATH,destination=/data atm_app
    Where ABSOLUTE_PATH - absolute path to /data folder(example for linux: /home/SenlaTestTask/data)

```
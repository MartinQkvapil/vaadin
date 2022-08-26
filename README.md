# Title: Language school 
# Description:
    Small application for creating language tests.
# Technologies:
    - Frontend: 
        - Vaadin
    - Backend:
        - JPA, MySQL (running in docker container)
        - Springboot, Spring
# Database 
    - for rebuild database use:  docker-compose up --build
    - for new build of workbench inserts you have to remove sql_mode and Default charset on table course and starting workbench definitions.
# Users:
    Access to user account:
        username: user
        password: user

    Access to admin account: 
        username: admin
        password: admin
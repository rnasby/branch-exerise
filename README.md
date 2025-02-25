- Resources:
    - /users/{username} [GET] - Get a user by username, and return the user's information. Example response: 
        ```json
        {
          user_name: "octocat",
          display_name: "The Octocat",
          avatar: "https://avatars3.githubusercontent.com/u/583231?v=4",
          geo_location: "San Francisco",
          email: null,
          url: "https://github.com/octocat ",
          created_at: "2011-01-25 18:44:36",
          repos: [{
              name: "boysenberry-repo-1",
              url: "https://github.com/octocat/boysenberry-repo-1"
            }, ...
          ]
        }
      ```

- Using:
    - To build, test, and install service jar in local maven repo:
       - In cloned repository, run "./mvnw clean install"

    - To manually test service:
        - In cloned repository, run with "./mvnw spring-boot:run"
        - Open browser and navigate to "http://localhost:8080/users/octocat".

- Decisions and Architecture:
    - Test proving implementation is ./src/test/java/com/branch/user/UserControllerIT.java
    - Did not implement rate limiting or caching since it was not required.  Would not be difficult to add in the
      future.
    - Error handling is minimal, but care was taken to not expose GitHub API exceptions to the client.
    - API versioning was not implemented, but would be easy to add in the future.
    - Library/Framework decisions beyond Spring, were limited to Lombok to reduce boilerplate code. No other libraries
      were used. If GitHub API needs were any more complex, I would have used a library, since there are several available,
      and they would already have rate limiting and caching built in. I expect there is a process for admitting 3rd party
      libraries for license, and vulnerabilities.

- Assuming these typos in assignment example response:
    - avatar_url: 
       - Example host "avatars3.githubusercontent.com" does not match actual host "avatars.githubusercontent.com". Assuming
         we want the actual host name from GitHub.
    - url:
       - Example url has space at end of value, but actual value from GitHub does not.
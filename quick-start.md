# Quick Start

## How Run the project

{% hint style="info" %}
All you gonna need to run this project is having Java JDK 17 installed on system and run the sql database file.
{% endhint %}

```java
mvn spring-boot:run
```

## Make your first request

To make your first request, send an authenticated request to the user endpoint. This will create a `user`, which is nice.

{% swagger src=".gitbook/assets/test (1).json" path="/user" method="post" %}
[test (1).json](<.gitbook/assets/test (1).json>)
{% endswagger %}

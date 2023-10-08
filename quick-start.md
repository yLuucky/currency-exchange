# Quick Start

## Get your API keys

{% hint style="warning" %}
Your API requests are authenticated using API keys. Any request that doesn't include an API key will return an error.
{% endhint %}

You can generate an API key from : [here](https://apilayer.com/marketplace/currency\_data-api?utm\_source=apilayermarketplace\&utm\_medium=featured)

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

# JavaRush Telegram Bot

JavaRush Telegram bot from community for community. Written by developers, who learned in [JavaRush](https://javarush.ru).

## Idea
The main idea is to show how to create real application, which can be used by someone else.
There are [set of posts](https://javarush.ru/groups/posts/2935-java-proekt-ot-a-do-ja-pishem-realjhnihy-proekt-dlja-portfolio), which are describing step by step guidelines of how it was created.

## MVP Scope
As a user, I want to subscribe on group of posts and get notification via telegram-bot every time,
when new post, related to group subscriptions, has come.

# How it would work
Based on MVP Scope, we can specify next behaviours (here and after Telegram User, which is using JavaRush Telgegram bot will call User):
- User can subscribe on group of articles
- User can view list of group subscriptions on which user subscribes
- User can unsubscribe from group of articles
- User can set an inactive bot and do not receive notifications
- User can restart getting notifications

## Deployment
Deployment process as easy as possible:
Required software:
- terminal for running bash scripts
- docker
- docker-compose
- directories for environments:
  
    <root project>/.env/jrtb.env
  
    File contents
    - BOT_USERNAME=< username value >
    - BOT_TOKEN=< token value >

to deploy application, switch to needed branch and run bash script:

$ bash start.sh

That's all.
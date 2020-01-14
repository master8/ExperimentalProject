This is an example for [issue](https://github.com/firebase/firebase-android-sdk/issues/1092)

# In-App Messaging - Message is not showing!
### “Removing display event listener”

I suppose, there is a problem with calling ```FirebaseInAppMessaging.clearDisplayListener()``` in ```FirebaseInAppMessagingDisplay.onActivityDestroyed``` without checking which activity has destroyed (current or previous). 
**As a result, the app can skip messages.**

### Case:
0. Get example app's Instance ID
1. Close example app
2. send test in-app message from firebase console
3. open app (open FirstActivity)
4. FirstActivity open SecondActivity
5. FirstActivity has finished.
6. SecondActivity still opened.
7. The message was received but not showing.


### Logs:

```
2019-12-24 13:01:07.241 17432-17432/com.master8.experimentalproject D/FIAM.Display: Created activity: com.master8.experimentalproject.FirstActivity
2019-12-24 13:01:07.292 17432-17432/com.master8.experimentalproject D/FIAM.Display: Started activity: com.master8.experimentalproject.FirstActivity
2019-12-24 13:01:07.292 17432-17432/com.master8.experimentalproject I/FIAM.Headless: Setting display event listener
2019-12-24 13:01:07.295 17432-17432/com.master8.experimentalproject I/FIAM.Headless: went foreground
2019-12-24 13:01:07.295 17432-17432/com.master8.experimentalproject D/FIAM.Headless: Event Triggered: ON_FOREGROUND
2019-12-24 13:01:07.295 17432-17432/com.master8.experimentalproject D/FIAM.Display: Resumed activity: com.master8.experimentalproject.FirstActivity
2019-12-24 13:01:07.297 17432-17533/com.master8.experimentalproject I/FIAM.Headless: Forcing fetch from service rather than cache. Test Device: true | App Fresh Install: true
2019-12-24 13:01:07.297 17432-17533/com.master8.experimentalproject I/FIAM.Headless: Recoverable exception while reading cache: /data/user/0/com.master8.experimentalproject/files/fiam_impressions_store_file (No such file or directory)
2019-12-24 13:01:07.300 17432-17533/com.master8.experimentalproject I/FIAM.Headless: Fetching campaigns from service.
2019-12-24 13:01:07.322 17432-17432/com.master8.experimentalproject I/FIAM.Headless: Removing display event listener
2019-12-24 13:01:07.322 17432-17432/com.master8.experimentalproject D/FIAM.Display: Pausing activity: com.master8.experimentalproject.FirstActivity
2019-12-24 13:01:07.352 17432-17432/com.master8.experimentalproject D/FIAM.Display: Created activity: com.master8.experimentalproject.SecondActivity
2019-12-24 13:01:07.389 17432-17432/com.master8.experimentalproject D/FIAM.Display: Started activity: com.master8.experimentalproject.SecondActivity
2019-12-24 13:01:07.389 17432-17432/com.master8.experimentalproject I/FIAM.Headless: Setting display event listener
2019-12-24 13:01:07.392 17432-17432/com.master8.experimentalproject D/FIAM.Display: Resumed activity: com.master8.experimentalproject.SecondActivity
2019-12-24 13:01:07.524 17432-17432/com.master8.experimentalproject D/FIAM.Display: Stopped activity: com.master8.experimentalproject.FirstActivity
2019-12-24 13:01:07.526 17432-17432/com.master8.experimentalproject I/FIAM.Headless: Removing display event listener
2019-12-24 13:01:07.526 17432-17432/com.master8.experimentalproject D/FIAM.Display: Destroyed activity: com.master8.experimentalproject.FirstActivity
2019-12-24 13:01:07.681 17432-17533/com.master8.experimentalproject I/FIAM.Headless: Successfully fetched 1 messages from backend
2019-12-24 13:01:07.682 17432-17533/com.master8.experimentalproject D/FIAM.Headless: Updating contextual triggers for the following analytics events: []
2019-12-24 13:01:07.690 17432-17533/com.master8.experimentalproject D/FIAM.Headless: Decoding message: # com.google.firebase.inappmessaging.MessagesProto$Content@533ba749
    banner {
      background_hex_color: "#3ca9ff"
      body {
        hex_color: "#ffffff"
        text: "\320\242\320\265\320\272\321\201\321\202 \321\201\320\276\320\276\320\261\321\211\320\265\320\275\320\270\321\217"
      }
      title {
        hex_color: "#ffffff"
        text: "\320\227\320\260\320\263\320\276\320\273\320\276\320\262\320\276\320\272"
      }
    }
```

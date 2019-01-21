# ANR-Detector
Report ANR (Application Not Responding)
We use this library for own problem solving purpose only. 

## Developed
[![Sandip](https://avatars1.githubusercontent.com/u/31722942?v=4&u=18643bfaaba26114584d27693e9891db26bcb582&s=39) Sandip](https://github.com/SandipLayek27)  
# ★ Gradle Dependency
Add Gradle dependency in the build.gradle file of your application module (app in the most cases) :
First Tab:

```sh
allprojects{
    repositories{
        jcenter()
        maven {
            url 'https://jitpack.io'
        }
    }
}
```

AND

```sh
dependencies {
	implementation 'implementation 'com.github.SandipLayek27:ANR-Detector:1.0'
}
```

# ★ Features are
1. ANR REPORT GENERATOR. 
[IF APPCATION NOT RESPONDING OCCURED THEN IT CAN GENERATE REPORT FILE. 
HOLD THIS REPORT FILE FROM START ACTIVITY FILE.
ALSO USE HERE UNCAUGHT EXCEPTION LIBRARY WHICH MAY HELP YOU HOLD THE ERROR/ANR REPORTS.]

★ CODE USES.
```
protected void onCreate(Bundle savedInstanceState) {
  //USE HERE UNCAUGHT EXCEPTION LIBRARY
  //Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));
  ANRWatchDog anrWatchDog = ((ANRWatchdogTestApplication) getApplication()).anrWatchDog;
  //anrWatchDog.setReportThreadNamePrefix("APP:");
  anrWatchDog.setReportMainThreadOnly();
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  try{
      String report = getIntent().getExtras().getString("CRASH_REPORT");
  }catch (Exception e){
      e.printStackTrace();
  }
  findViewById(R.id.simpleAll).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
          int i = 0;
          while (true){
              i++;
          }
      }
  });
}
```

# ★ Uses of above Features
* 1. ANR REPORT GENERATOR.
```sh
  //USE IT BEFORE super oncreate SECTION.
  ANRWatchDog anrWatchDog = ((ANRWatchdogTestApplication) getApplication()).anrWatchDog;
  //anrWatchDog.setReportThreadNamePrefix("APP:");
  anrWatchDog.setReportMainThreadOnly();
```

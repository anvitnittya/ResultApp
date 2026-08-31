# ResultApp – Android Studio Project

Based on `Result.xlsx` (uploaded by the user). The workbook contains Home, Class Info, Student info, seven core subject mark sheets, Ekatrit, ghosh, Final Result, Card Sheet and Ghoshwara.

## Included
- Kotlin + Jetpack Compose UI
- Room/SQLite local database
- 120 students imported from the workbook
- Marks imported for English, Marathi, Physics, Chemistry, Biology, Math and Geography
- Offline student list and result-card screen
- Workbook-style calculation: Term A = UT1 + Theory; Term B = UT2 + Theory + Practical; Average = CEILING((A+B)/2)
- Pass threshold = 35

## Open in Android Studio
1. Open this folder in Android Studio.
2. Use JDK 17.
3. Allow Gradle sync to download dependencies.
4. Build > Build APK(s).

The generated debug APK will normally be under `app/build/outputs/apk/debug/`.

## Notes
- The original workbook has additional presentation/printing sheets and some special formulas. This first native version focuses on the core student/marks/result workflow.
- Environmental Studies and Physical Education are represented as grade-only fields in the source workbook and are not yet editable in this UI.
- The workbook's Card Sheet uses a 600-mark percentage base while summing seven displayed academic subjects; the app keeps that 600 base to match the source workbook's result convention.

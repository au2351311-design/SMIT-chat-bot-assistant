# SMIT Chatbot Assistant (Java AWT & Swing)

Desktop app for SMIT CSE-B students with dashboard, chat assistant, class timetable, exam timetable, subject/faculty list and countdowns.

## Requirements
- JDK 17 or newer (JDK 21 recommended)
- VS Code (or another Java IDE)

## Add the SMIT logo
Save the official logo as:
`assets/SMIT-LOGO.png`

If the local file is absent, the application tries to load:
https://smit.edu.in/wp-content/uploads/2026/02/SMIT-LOGO.png

The logo URL fallback requires an internet connection. Local logo is recommended.

## Compile and run
Run these commands from the project root:

```powershell
javac -encoding UTF-8 -d out src/Main.java
java -cp out Main
```

## Included pages
- Dashboard with next exam countdown
- Chat Assistant with quick questions and message bubbles
- Class timetable Day 1-Day 6
- End-semester examination timetable
- Subjects and faculty table

## Notes
- Registration number is not included.
- Exam session is displayed as FN (Forenoon), as provided. Exact exam start time was not included in the supplied exam timetable.
- Day 1-Day 6 labels are preserved; they are not mapped to weekdays.
- Timetable and FAQ answers are predefined in `src/Main.java`.

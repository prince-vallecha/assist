# Scheduler — get an installable APK with no IDE

This is a complete, buildable Android project. It won't compile on its own —
it needs a real internet connection to Google's Maven repo, which most
sandboxed tools don't have — but GitHub's build servers do, for free.

## Steps

1. **Create a repo.** Go to github.com (sign up if you don't have an
   account), click **New repository**, name it whatever you like (e.g.
   `scheduler`), leave it empty (no README/gitignore), and create it.

2. **Upload this project.** On the new repo's page, click
   **Add file → Upload files**, then drag this entire folder's contents in
   (including the hidden `.github` folder — if your file browser hides it,
   show hidden files first, or use `git` if you have it installed:
   `git init && git add . && git commit -m "init" && git branch -M main && git remote add origin <your-repo-url> && git push -u origin main`).
   Commit the upload.

3. **Run the build.** Go to the **Actions** tab on your repo. You should see
   a workflow called "Build APK" — click it, then **Run workflow** (or it
   runs automatically since it also triggers on push to `main`).

4. **Download the APK.** Once the run finishes (green checkmark, usually
   2-4 minutes), open it, scroll to **Artifacts**, and download
   `scheduler-debug-apk`. It's a zip containing `app-debug.apk`.

5. **Install it.** Transfer the APK to your phone (or just do steps 1-4
   from your phone's browser — GitHub's mobile site supports all of this).
   Open the APK file, allow "install unknown apps" for whichever app you
   used to open it, and install.

6. **Set it as your assistant.** Open the Scheduler app once (grants mic
   permission), tap "Open assistant settings," and select Scheduler as your
   Digital assistant app. Hold the power button to test.

## Note on the debug build
This workflow builds a debug APK, which Android's build tools auto-sign
with a throwaway debug key — that's why it installs without you generating
a signing key yourself. It's fine for personal use on your own device; a
release build (for distributing it more broadly later) needs a proper
signing key, which is a separate step for when you get there.

## When you outgrow this workflow
Once you're actively iterating — adding the PDF parser, the scheduling
engine, notification channels — pushing to GitHub and waiting on a cloud
build every time will get slow. At that point, either:
- Install Android Studio (one installer, includes everything, lets you
  build straight to your phone over USB in seconds), or
- Use Claude Code on your own machine, which has normal internet access
  and can run these Gradle builds directly and iterate with you.

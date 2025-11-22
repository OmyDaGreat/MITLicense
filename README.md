# MIT License Display - Hacker Edition 🕸️

A single-page website featuring a Matrix rain effect behind the MIT License, with a hacker-themed design in neon green and black.

## Features

- 🌧️ **Animated Katakana Matrix Rain Effect** - Falling Japanese characters in the background
- 💚 **Hacker Theme** - Neon green (#00ff00) on black with glowing effects
- 🖼️ **GitHub Profile Integration** - Displays profile picture with animated glow
- 📜 **MIT License Display** - Clean, readable license text with hacker aesthetics
- 📱 **Responsive Design** - Works on desktop and mobile devices

This is a [Kobweb](https://github.com/varabyte/kobweb) project showcasing modern web technologies.

## Getting Started

### Running in Development Mode

Run the development server from the project root:

```bash
./gradlew :site:kobwebStart
```

Or using Kobweb CLI from the site folder:

```bash
cd site
kobweb run
```

Open [http://localhost:8080](http://localhost:8080) with your browser to see the Matrix rain effect and the MIT license.

### Stopping the Server

```bash
./gradlew :site:kobwebStop
```

Or press `Q` in the terminal if using `kobweb run`.

You can use any editor you want for the project, but we recommend using **IntelliJ IDEA Community Edition** downloaded
using the [Toolbox App](https://www.jetbrains.com/toolbox-app/).

Press `Q` in the terminal to gracefully stop the server.

### Live Reload

Feel free to edit / add / delete new components, pages, and API endpoints! When you make any changes, the site will
indicate the status of the build and automatically reload when ready.

## Exporting for Production

Export the site to static files:

```bash
./gradlew :site:kobwebExport
```

The exported site will be in `site/.kobweb/site/` directory. You can deploy this to any static hosting service like:
- GitHub Pages
- Netlify
- Vercel
- Firebase Hosting

For production server mode:

```bash
kobweb run --env prod
```

For cloud deployment without interactive terminal:

```bash
kobweb run --env prod --notty
```

## Technology Stack

- **Kobweb** - Kotlin web framework built on Compose HTML
- **Kotlin/JS** - Kotlin compiled to JavaScript
- **Canvas API** - For Matrix rain animation
- **CSS Animations** - Glow and flicker effects

## Customization

The main page is located at `site/src/jsMain/kotlin/xyz/malefic/staticsite/pages/Index.kt`. You can customize:
- Matrix rain speed and characters
- Color scheme (currently #00ff00 neon green)
- Profile picture URL
- License text and copyright year
- Animation effects
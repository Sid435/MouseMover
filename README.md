# Mouse Mover

## Table of Contents
1. [Introduction](#introduction)
2. [Features](#features)
3. [Requirements](#requirements)
4. [Installation](#installation)
5. [Usage](#usage)
6. [Configuration](#configuration)
7. [Troubleshooting](#troubleshooting)
8. [Contributing](#contributing)

## Introduction

Mouse Mover is a Java Swing application designed to simulate mouse activity at regular intervals. This tool is useful for preventing screen locks or maintaining an "active" status on various systems.

## Features

- User-friendly graphical interface
- Customizable movement interval (1-60 minutes)
- Start/Stop functionality
- Random mouse movement across the screen
- Splash screen on application startup
- Java version check to ensure compatibility

## Requirements

- Java Development Kit (JDK) 1.8 or higher
- Operating system with graphical interface support

## Installation

1. Clone the repository or download the source code:
   ```
   git clone https://github.com/yourusername/mouse-mover.git
   ```

2. Navigate to the project directory:
   ```
   cd mouse-mover
   ```

3. Compile the Java files:
   ```
   javac MouseMover.java MouseMoverApp.java
   ```

## Usage

1. Run the application:
   ```
   java MouseMover
   ```

2. The application will display a splash screen for 3 seconds before showing the main interface.

3. In the main window:
   - Set the desired interval (in minutes) using the spinner.
   - Click "Start" to begin the mouse movement.
   - Click "Stop" to halt the mouse movement.

4. The application will move the mouse cursor to random positions on the screen at the specified interval.

## Configuration

- Splash Screen Image: Replace the image file at `/Users/siddharthjha/Downloads/Mouse Jiggler/spImg.jpg` with your desired splash screen image.

- Interval Range: The default range is 1-60 minutes. To modify this, adjust the `SpinnerNumberModel` parameters in the `MouseMoverApp` constructor.

## Troubleshooting

- If you receive a "JDK level 1.8 or above required" warning, ensure you have the correct Java version installed and that your system is using the correct Java version.

- If the mouse movement doesn't work, ensure your system allows Java applications to control the mouse. You may need to grant additional permissions.

## Contributing

Contributions to the Mouse Mover project are welcome! Please follow these steps:

1. Fork the repository
2. Create a new branch for your feature
3. Commit your changes
4. Push to your branch
5. Create a new Pull Request


---

For any additional questions or support, please open an issue in the GitHub repository.

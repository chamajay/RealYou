# RealYou

![GitHub Release](https://img.shields.io/github/v/release/chamajay/RealYou)
![GitHub License](https://img.shields.io/github/license/chamajay/RealYou)

<p align="justify">Ever wondered how others see you? <b>RealYou</b> is a simple Android application that acts as a reverse mirror, showing you the real you—exactly as others see you.</p>

<a href="#screenshots">Screenshots</a> &bull; <a href="#features">Features</a> &bull; <a href="#installation">Installation</a> &bull; <a href="#license">License</a>

## Screenshots

<p align="justify"><b>Note:</b> The app uses the device's front camera to display a live preview of the user's face. The Mona Lisa image is used here for demonstration purposes only and is not part of the app.</p>

<table align="center">
  <tr>
    <td align="center"><img src="https://github.com/user-attachments/assets/283f35b6-e671-40df-b28b-e074b1d41177" width="80%" /></td>
    <td align="center"><img src="https://github.com/user-attachments/assets/75ef769e-5c12-434a-836b-33b8f903c757" width="80%" /></td>
    <td align="center"><img src="https://github.com/user-attachments/assets/ec58c180-56bf-4a8e-aa0e-309251117751" width="80%" /></td>
  </tr>
  <tr>
    <td align="center" colspan="3"><img src="https://github.com/user-attachments/assets/1aa7cb0c-c487-4d2c-a419-5860bb45a10e" width="95%" /></td>
  </tr>
</table>

<p align="justify"><sub>The image used in the app screenshots feature the Mona Lisa painting by Leonardo da Vinci. This image is licensed under the <a href="https://creativecommons.org/licenses/by-sa/3.0/deed.en">Creative Commons Attribution-Share Alike 3.0 Unported license</a>. The original work is available on <a href="https://commons.wikimedia.org/wiki/File:Mona_Lisa,_by_Leonardo_da_Vinci,_from_C2RMF_Repaired.jpg">Wikimedia Commons</a>.</sub></p>

## Features

- **Real and Mirrored Views:** Easily switch between the real and mirrored views.

- **Side-by-Side Comparison:**  In landscape mode, view both real and mirrored views simultaneously for an easy comparison.

- **Gesture Support:** Quickly switch between views with a simple double-tap.

- **Material 3 Design:** Built with Google's latest Material 3 (Material You) guidelines for a clean and user-friendly experience.

## Installation

### 1. GitHub Release

[<img src="https://github.com/user-attachments/assets/0c087a45-66a7-4e1e-bbe9-10c7240d7ecc"
      alt="Get it on GitHub"
      height="70">](https://github.com/chamajay/RealYou/releases)

### 2. Build From Source

To build RealYou from the source code, follow these steps:

1. Clone the repository:

    ```
    git clone https://github.com/chamajay/RealYou.git
    ```

2. Build the debug apk:

    - In the terminal, navigate to the project directory:

        ```
        cd RealYou
        ```

    - Make sure [Android SDK path](https://developer.android.com/tools/variables) is set properly.

    - Run the following command to build the debug apk:

        ```
        ./gradlew assembleDebug
        ```

    - The generated apk will be located in `app/build/outputs/apk/debug/`.

3. Install and run the app.

## License

<img src="https://www.gnu.org/graphics/gplv3-127x51.png">

This project is licensed under the GNU General Public License v3.0. See the [LICENSE](LICENSE) file for details.

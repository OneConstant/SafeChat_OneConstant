/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,js}"],
  theme: {
    extend: {
      colors: {
        secondary: '#FFBE18',
        dark: '#0C0E12',
        light: 'white',
        lightGray: '#a0a0a0'

      },
      fontFamily: {
        sans: ['Kanit', 'sans-serif']
      },
      backgroundImage: {
        'hero': "url('/src/Images/new.jpg')",
        'pattern': "url('/src/Images/loginbg.jpg')",
      },
    },
  },
  plugins: [],
}

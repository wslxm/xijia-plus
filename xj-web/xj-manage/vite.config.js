// vite.config.js
import { defineConfig } from 'vite';
import prismjs from 'vite-plugin-prismjs';

export default defineConfig({
    plugins: [
        prismjs({
            languages: 'all',
        }),
    ],
});
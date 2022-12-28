const components = require('prismjs/components');
const allLanguages = Object.keys(components.languages).filter((item) => item !== 'meta');

module.exports = {
    presets: [
        '@vue/app'
    ],
    plugins: [
        [
            'prismjs',
            {
                languages: allLanguages,
            },
        ],
    ],
}
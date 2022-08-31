module.exports = {
  root: true,
  env: {
    node: true
  },
  'extends': [
    'plugin:vue/essential',
    'eslint:recommended'
  ],
  rules: {
    'no-console': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    // 关闭组件命名规则检查
    "vue/multi-word-component-names": "off",
    // 关闭未使用参数检查
    "no-unused-vars": "off",
  },
  parserOptions: {
    parser: 'babel-eslint'
  }
};
import { defineConfig } from 'vitepress'

// https://vitepress.dev/reference/site-config
export default defineConfig({
  title: "Indexium",
  description: "An Apache Lucene based Search Engine API.",
  themeConfig: {
    nav: [
      { text: 'Home', link: '/' },
      { text: 'Examples', link: '/quickstart/examples' },
      { text: 'Guide', link: '/guide' }
    ],

    sidebar: [
      {
        text: 'Guide',
        items: [
          { text: 'Getting Started', link: '/guide/' },
          { text: 'Installation', link: '/guide/installation' },
        ]
      },
      {
        text: 'Usage',
        items: [
          { text: 'Schemas', link: '/quickstart' },
          { text: 'Indexer', link: '/quickstart/indexer' },
          { text: 'Searcher', link: '/quickstart/searcher' },
          { text: 'Code Examples', link: '/quickstart/examples' },
        ]
      }
    ],

    socialLinks: [
      { icon: 'github', link: 'https://github.com/Rayanworkout/Indexium' }
    ]
  }
})

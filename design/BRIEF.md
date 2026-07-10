# Design Brief — DevAgent Demo Landing Page

**Issue:** #38 (Spike — design exploration, no product code)
**Scope:** Visual design direction for a demo landing page. This document is the deliverable.
**Supplementary:** `design/mockup-option-b.html` — self-contained HTML preview of the recommended direction (inline CSS only, no scripts).

---

## 1. Problem

DevAgent Demo is a Spring Boot 3 (Java 17) service exposing a handful of plain-text
greeting endpoints:

| Endpoint | Behavior |
|---|---|
| `GET /greet?name=&lang=` | Personalized greeting, `en`/`es`, graceful fallbacks |
| `GET /hello?name=` | Legacy demo greeting |
| `GET /hello2` | Generic demo greeting |
| `GET /hola` | Fixed Spanish greeting |
| `GET /health`, `GET /ping` | Liveness (`OK` / `pong`) |
| `GET /goodbye` | Farewell text |

Today, hitting the root URL returns a Whitelabel error page. There is no visual
surface that (a) tells a visitor what this service is, (b) demonstrates the greeting
API — the actual point of the demo, or (c) reflects that this repo is a showcase of
agent-driven development.

**Goal of the landing page:** a single static page (servable later from
`src/main/resources/static/index.html`) that introduces the demo, documents the
endpoints, and invites the visitor to try the API. The page must work with **zero
build tooling** (no npm, no bundler — this is a Maven-only repo) and ideally zero
JavaScript, since the endpoints are plain `GET`s that a browser can hit directly.

**Audience:** developers evaluating the DevAgent workflow — people who read code,
appreciate restraint, and will judge the page by clarity, not flash.

---

## 2. Design options explored

### Option A — "Terminal minimal" (developer docs aesthetic)

Single narrow column, near-monochrome, monospace-forward. The page reads like a
beautiful README: title, one paragraph, a table of endpoints rendered as `code`
rows, footer. No hero, no color beyond one accent.

- **Layout:** one column, max-width ~640px, generous vertical rhythm.
- **Type:** monospace for everything interactive (`JetBrains Mono` stack falling back to `ui-monospace`), system sans for prose.
- **Color:** dark background (`#0d1117`-ish), single green or amber accent — terminal connotation.
- **Pros:** trivially cheap to build; on-brand for a dev tool; ages well; effortless dark mode (it *is* dark mode).
- **Cons:** doesn't demonstrate anything — it's documentation, not a demo; no moment of delight; visually indistinguishable from a thousand GitHub READMEs; weak visual hierarchy when the endpoint list grows.

### Option B — "Interactive playground" (hero + live try-it panel) ★ recommended

A compact hero states what the service is; the centerpiece is a **playground card**
where the visitor picks a name and language and fires a real request to `/greet`.
Because the endpoints are plain GETs returning text, the try-it panel can be a plain
HTML `<form method="get" action="/greet">` — **fully functional with zero
JavaScript**. Below it, an endpoint reference grid.

- **Layout:** centered column (~880px), three stacked bands: hero → playground → endpoint grid → footer.
- **Type:** a friendly geometric sans for headings (system stack: `Avenir/Segoe UI/system-ui`), monospace only where content is code (URLs, responses).
- **Color:** light-first with warm off-white ground, one confident indigo/violet accent for the hero gradient and primary button; `prefers-color-scheme: dark` variant.
- **Pros:** actually *demonstrates* the API — the demo demos itself; the form-GET trick keeps it JS-free and build-free; clear hierarchy (one hero, one call to action); the endpoint grid scales as endpoints are added; bilingual greeting gives the hero a natural motif ("Hello / Hola").
- **Cons:** slightly more CSS to write and maintain than A; submitting the form navigates to the raw text response (acceptable for a demo; a JS enhancement could inline the response later); the accent/gradient choice needs care to avoid looking like a generic SaaS template.

### Option C — "Status dashboard" (ops aesthetic)

Frame the page as a mini status board: big uptime tile wired to `/health`, latency
tile for `/ping`, endpoint cards with method chips, dense grid layout.

- **Layout:** 12-column responsive grid of tiles, header bar with service name and a green "operational" dot.
- **Type/Color:** neutral grays, semantic green/amber/red chips, tabular numerals.
- **Pros:** looks sophisticated; makes `/health` and `/ping` feel purposeful; familiar mental model (status pages).
- **Cons:** dishonest without JavaScript — live tiles require `fetch` polling, so either the page needs JS or the "dashboard" is fake static chrome; over-engineered for six endpoints; buries the greeting API, which is the actual star; highest build and maintenance cost.

---

## 3. Recommendation: Option B — Interactive playground

Rationale:

1. **It demonstrates instead of describing.** The repo exists to show off a working
   service built by agent-driven tasks; a page where you type "Ada", pick
   *Español*, and get `¡Hola, Ada!` back proves the whole stack in one click.
2. **Zero-JS, zero-build is a real constraint here**, and Option B is the only
   direction that is both interactive *and* pure HTML/CSS — a native `<form
   method="get">` maps exactly onto the existing `/greet` contract, including its
   blank-name and unsupported-language fallbacks (they become demo features, not
   edge cases).
3. **Right-sized.** One accent color, one card component, one grid — more presence
   than A, a fraction of C's complexity.
4. **Extensible.** New endpoints slot into the reference grid; a later JS
   enhancement (inline response via `fetch`) upgrades the playground without
   redesign.

**Visual direction (Option B specifics):**

- **Palette (light):** ground `#faf9f7` (warm off-white); ink `#1c1b22`; accent
  indigo `#5b5bd6` with hero gradient toward violet `#8b5cf6`; card surface `#ffffff`
  with 1px `#e6e4df` border; muted text `#6f6d78`.
- **Palette (dark, via `prefers-color-scheme`):** ground `#141318`; surface
  `#1e1d24`; ink `#f0eff4`; same accents lifted ~10% lightness for contrast.
- **Contrast:** all text pairs ≥ 4.5:1 (WCAG AA); accent-on-ground reserved for
  large text and borders.
- **Typography:** system sans stack for UI/prose (`system-ui, -apple-system, "Segoe
  UI", sans-serif`); `ui-monospace` stack for endpoint paths and responses. Scale:
  hero 44/48, section heading 24, body 16/1.6, code 14. No webfonts (self-contained,
  no external requests).
- **Motif:** the bilingual pair — hero headline renders "Hello. / ¡Hola!" with the
  Spanish half in the accent gradient; playful but derived directly from the API.
- **Shape language:** 12px card radius, 8px control radius, soft single-layer
  shadows (`0 1px 3px rgb(0 0 0 / .08)`), no glassmorphism/no heavy gradients
  outside the hero text.
- **Spacing:** 8px base unit; bands separated by 64–96px on desktop, 40–56px mobile.

---

## 4. Wireframes (Option B)

### Screen 1 — Landing page, desktop (default state)

```
+----------------------------------------------------------------------+
|  ◆ DevAgent Demo                                   [ GitHub repo ↗ ] |   header
+----------------------------------------------------------------------+
|                                                                      |
|                        Hello.  ¡Hola!                                |   hero
|                        ~~~~~~  ~~~~~~ (accent gradient on "¡Hola!")  |
|          A tiny Spring Boot greeting API, built end-to-end           |
|          by agent-driven tasks. Try it right here.                   |
|                                                                      |
|   +--------------------------------------------------------------+   |
|   |  TRY THE API                                    GET /greet   |   |   playground
|   |                                                              |   |   card
|   |  Name                          Language                      |   |
|   |  +----------------------+      ( • ) English   (  ) Español  |   |
|   |  |  Ada                 |                                     |  |
|   |  +----------------------+      [   Send greeting →   ]        |  |
|   |                                                              |   |
|   |  Request preview:                                            |   |
|   |  `GET /greet?name=Ada&lang=en`                               |   |
|   |                                                              |   |
|   |  Tip: leave the name blank or pick an unsupported language   |   |
|   |  to see the graceful fallbacks.                              |   |
|   +--------------------------------------------------------------+   |
|                                                                      |
|   ALL ENDPOINTS                                                      |
|   +------------------+  +------------------+  +------------------+   |   endpoint
|   | GET /greet       |  | GET /hello       |  | GET /hola        |   |   grid
|   | Personalized     |  | Legacy demo      |  | Fixed Spanish    |   |   (3-col)
|   | greeting, en/es  |  | greeting         |  | greeting         |   |
|   | [ Open ↗ ]       |  | [ Open ↗ ]       |  | [ Open ↗ ]       |   |
|   +------------------+  +------------------+  +------------------+   |
|   +------------------+  +------------------+  +------------------+   |
|   | GET /health      |  | GET /ping        |  | GET /goodbye     |   |
|   | Liveness check   |  | Returns "pong"   |  | Farewell text    |   |
|   | [ Open ↗ ]       |  | [ Open ↗ ]       |  | [ Open ↗ ]       |   |
|   +------------------+  +------------------+  +------------------+   |
|                                                                      |
|   Spring Boot 3 · Java 17 · built by DevAgent            © 2026     |   footer
+----------------------------------------------------------------------+
```

### Screen 2 — Landing page, mobile (~375px)

```
+----------------------------+
| ◆ DevAgent Demo   [GitHub] |   header (single row)
+----------------------------+
|                            |
|        Hello.              |   hero (stacked,
|        ¡Hola!              |   left-aligned)
|                            |
|  A tiny Spring Boot        |
|  greeting API. Try it.     |
|                            |
| +------------------------+ |
| | TRY THE API  GET /greet| |   playground
| |                        | |   (controls stack
| | Name                   | |   vertically)
| | +--------------------+ | |
| | | Ada                | | |
| | +--------------------+ | |
| |                        | |
| | Language               | |
| | (•) English            | |
| | ( ) Español            | |
| |                        | |
| | [  Send greeting →  ]  | |   full-width button
| |                        | |
| | GET /greet?name=Ada    | |
| +------------------------+ |
|                            |
| ALL ENDPOINTS              |
| +------------------------+ |   endpoint grid
| | GET /greet             | |   collapses to
| | Personalized, en/es    | |   1 column
| +------------------------+ |
| +------------------------+ |
| | GET /hello             | |
| +------------------------+ |
|          ...               |
|                            |
| Spring Boot 3 · Java 17    |   footer
+----------------------------+
```

### Screen 3 — Response state (after "Send greeting")

With zero JS, the form GET navigates to the plain-text response. The designed
target state (phase 2, small progressive-enhancement script) renders it inline:

```
+--------------------------------------------------------------+
|  TRY THE API                                    GET /greet    |
|                                                               |
|  Name                          Language                       |
|  +----------------------+      (  ) English   ( • ) Español   |
|  |  Ada                 |                                     |
|  +----------------------+      [   Send greeting →   ]        |
|                                                               |
|  Response                                     200 OK          |
|  +--------------------------------------------------------+   |
|  |  ¡Hola, Ada!                                           |   |   response well:
|  +--------------------------------------------------------+   |   mono, accent
|  `GET /greet?name=Ada&lang=es`                                |   left border
+--------------------------------------------------------------+
```

Fallback-demonstration variants of the same state (copy shown in the response well):

```
name blank            → | ¡Hola!            |   caption: "Blank name → generic greeting"
lang=fr (unsupported) → | Hello, Ada!       |   caption: "Unknown language → falls back to English"
```

### Component hierarchy

```
LandingPage
├── SiteHeader
│   ├── BrandMark            (◆ glyph + "DevAgent Demo" wordmark)
│   └── RepoLink             (external link, quiet button style)
├── Hero
│   ├── Headline             ("Hello." plain ink / "¡Hola!" gradient accent)
│   └── Subhead              (one-sentence value prop)
├── PlaygroundCard
│   ├── CardHeader
│   │   ├── SectionLabel     ("TRY THE API", letterspaced caps)
│   │   └── EndpointChip     (mono "GET /greet")
│   ├── GreetingForm         (<form method="get" action="/greet">)
│   │   ├── NameField        (label + text input, name="name")
│   │   ├── LanguageChoice   (radio group, name="lang": en / es)
│   │   └── SubmitButton     (accent, primary)
│   ├── RequestPreview       (mono, reflects current field values*)
│   ├── ResponseWell         (phase-2 only: status chip + mono response text)
│   └── FallbackHint         (muted tip about blank name / unknown lang)
├── EndpointSection
│   ├── SectionLabel         ("ALL ENDPOINTS")
│   └── EndpointGrid         (3-col desktop / 1-col mobile)
│       └── EndpointCard ×6
│           ├── EndpointChip (mono "GET /path")
│           ├── Description  (one line)
│           └── OpenLink     (plain <a href="/path">, opens raw response)
└── SiteFooter
    ├── StackNote            ("Spring Boot 3 · Java 17 · built by DevAgent")
    └── Copyright

*RequestPreview is static example text in the zero-JS phase; live-updating in phase 2.
```

---

## 5. Acceptance criteria (for the implementation task)

1. **Single self-contained file:** `src/main/resources/static/index.html` served at
   `/`; all CSS inline or in one adjacent stylesheet; **no external resources**
   (no webfonts, CDNs, images) and **no JavaScript** in phase 1.
2. **Working playground:** the form performs a native `GET` to `/greet` with `name`
   and `lang` params matching `GreetController`'s contract; submitting with a blank
   name or a non-`es` language demonstrably hits the documented fallbacks.
3. **Complete endpoint reference:** all six current endpoints (`/greet`, `/hello`,
   `/hello2` may be grouped with `/hello`, `/hola`, `/health`, `/ping`, `/goodbye`)
   appear as cards with a one-line description and a working link.
4. **Visual direction as specified:** warm off-white ground, indigo→violet accent
   used only for hero highlight and primary button, card radius 12px, system font
   stacks, mono for code; matches the wireframes above in structure and hierarchy.
5. **Responsive:** grid is 3-column ≥ 900px, 1-column ≤ 600px; no horizontal
   scrolling at 320px; playground controls stack on mobile per Screen 2.
6. **Dark mode:** honors `prefers-color-scheme: dark` with the dark palette in §3;
   all text meets WCAG AA (≥ 4.5:1) in both themes.
7. **Accessibility:** semantic landmarks (`header/main/footer`), one `h1`, form
   inputs with visible `<label>`s, radio group in a `<fieldset>`/`<legend>`,
   visible focus states on all interactive elements.
8. **No backend changes** in phase 1 (static resource only). Phase 2 (optional,
   separate task): ≤ 30 lines of inline JS to `fetch` the greeting and render it in
   the ResponseWell with the status chip, degrading gracefully to the native form
   GET when JS is unavailable.
9. **Existing tests unaffected:** `mvn test` still passes; `/health` and `/ping`
   behavior unchanged.

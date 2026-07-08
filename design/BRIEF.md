# Design Brief — About Page for DevAgent Demo

**Issue:** [#22 Design an About page for the demo app](https://github.com/perly6185-lab/devdemo/issues/22)
**Milestone:** M2 · **Area:** cross-cutting · **Type:** task · **Risk:** low · **Priority:** p2
**Status of this document:** design only — no product code is changed.

---

## 1. Problem

The demo app (`com.devagent.devdemo`) is today a bare Spring Boot REST service: `HelloController`
exposes a handful of plain-text endpoints (`/hello`, `/health`, `/ping`, `/hola`, `/goodbye`) and
there is **no view layer** — no HTML, no static resources beyond `application.properties`, no CSS.

We want a simple, static **About** page that explains what the demo is and who is behind it. This
ticket asks for the **visual design only**: an ASCII wireframe, a component hierarchy, and a
self-contained HTML mockup. No implementation, no routing wiring, no product-code changes.

Because there is no existing UI or design system, the brief also has to establish the minimal visual
language (single column, system fonts, one accent colour) that a later build would inherit.

### Constraints observed
- **Static and self-contained.** Mockup uses inline CSS only — no scripts, no external fonts/images/CDNs.
- **No framework.** The app has no templating engine (Thymeleaf/Freemarker) and no front-end build. The
  design must degrade to a single hand-writable `.html` file so implementation stays trivial.
- **Content is placeholder.** Team names/roles are demo stand-ins; copy is illustrative.

---

## 2. Acceptance criteria (from the issue)

- [ ] `design/BRIEF.md` contains an **ASCII wireframe** of the About page **and** a **component hierarchy**.
- [ ] `design/about.html` is a **self-contained HTML mockup**, **inline CSS only, no scripts**.
- [ ] The mockup shows all four required regions: **header**, **intro blurb**, **small team list**, **footer**.
- [ ] No product code is modified (design artifacts under `design/` only).

### Additional acceptance criteria proposed for the follow-up *build* (not this ticket)
- [ ] Page is reachable at a stable path (recommend `/about`, see Options) and returns HTTP 200.
- [ ] Renders correctly with no network access (fully self-contained; no external requests).
- [ ] Readable at 360 px (mobile) through 1280 px (desktop) with no horizontal scroll.
- [ ] Passes a basic a11y bar: one `<h1>`, semantic landmarks (`header`/`main`/`footer`), text
      contrast ≥ 4.5:1, page has a `<title>` and `lang` attribute.

---

## 3. Wireframes

### 3.1 About page — desktop (recommended layout: single centered column)

```
+----------------------------------------------+
|  HEADER                                       |
|  ┌────────────────────────────────────────┐  |
|  │  ◆ DevAgent Demo            About  API  │  |  <- brand + nav
|  └────────────────────────────────────────┘  |
+----------------------------------------------+
|              MAIN  (max-width ~720px)         |
|                                               |
|   ┌───────────────────────────────────────┐  |
|   │  H1  About DevAgent Demo               │  |
|   │  ─────────────────────────────────────│  |
|   │  Intro blurb — 2–3 sentences on what   │  |  <- intro section
|   │  the demo is and why it exists.        │  |
|   └───────────────────────────────────────┘  |
|                                               |
|   H2  The team                                |
|   ┌───────────────────────────────────────┐  |
|   │ (AV) Ada Lovelace   — Lead Engineer    │  |
|   ├───────────────────────────────────────┤  |  <- team list
|   │ (GH) Grace Hopper   — Backend          │  |     (rows, avatar
|   ├───────────────────────────────────────┤  |      initials + role)
|   │ (AT) Alan Turing    — Platform         │  |
|   └───────────────────────────────────────┘  |
|                                               |
+----------------------------------------------+
|  FOOTER                                       |
|  ┌────────────────────────────────────────┐  |
|  │  © 2026 DevAgent Demo · v1.0.0 · GitHub │  |  <- footer meta
|  └────────────────────────────────────────┘  |
+----------------------------------------------+
```

### 3.2 About page — mobile (~360px, single column, nav stacks/wraps)

```
+---------------------------+
| ◆ DevAgent Demo           |
|              About   API  |  <- nav wraps under brand
+---------------------------+
|                           |
|  H1 About DevAgent Demo   |
|  ───────────────────────  |
|  Intro blurb text, 2–3    |
|  sentences, wraps freely. |
|                           |
|  H2 The team              |
|  ┌─────────────────────┐  |
|  │ (AV) Ada Lovelace    │  |
|  │      Lead Engineer   │  |  <- role drops to
|  ├─────────────────────┤  |     second line
|  │ (GH) Grace Hopper    │  |
|  │      Backend         │  |
|  ├─────────────────────┤  |
|  │ (AT) Alan Turing     │  |
|  │      Platform        │  |
|  └─────────────────────┘  |
|                           |
+---------------------------+
| © 2026 · v1.0.0 · GitHub  |
+---------------------------+
```

### 3.3 Empty state — no team members configured

```
|   H2  The team                                |
|   ┌───────────────────────────────────────┐  |
|   │   Team roster coming soon.             │  |  <- muted placeholder
|   └───────────────────────────────────────┘  |     (no broken rows)
```

*No loading/error states apply: the page is fully static with no data fetching.*

---

## 4. Component hierarchy

```
AboutPage (single static HTML document)
│
├── SiteHeader                     <header>
│   ├── Brand                      logo mark ◆ + "DevAgent Demo"
│   └── Nav                        links: About (current), API
│
├── Main                          <main>, centered column max-width ~720px
│   ├── IntroSection               <section>
│   │   ├── PageTitle              <h1> "About DevAgent Demo"
│   │   └── IntroBlurb             <p> 2–3 sentence description
│   │
│   └── TeamSection                <section>
│       ├── SectionHeading         <h2> "The team"
│       └── TeamList               <ul>
│           └── TeamMember (×N)     <li>
│               ├── Avatar          initials in a circle (no image asset)
│               ├── MemberName      person's name
│               └── MemberRole      role / title
│
└── SiteFooter                     <footer>
    └── FooterMeta                  © year · version · GitHub link
```

---

## 5. Interaction notes

- **Nav links** — `About` marks the current page (non-navigating / `aria-current`); `API` is a
  placeholder pointing at the existing REST endpoints (e.g. `/hello`). Purely navigational; no JS.
- **Team list** — static markup. Each row is a `<li>`; avatar is CSS-rendered **initials**, so there
  are **no image assets** to host or 404. Rows are keyboard-focusable only if wrapped in links (not
  required for v1).
- **Footer GitHub link** — external link to the repo; `target` optional.
- **States:** only two matter for a static page — *populated* (3.1/3.2) and *empty* (3.3). No loading
  or error states because nothing is fetched at runtime.
- **Data source:** all content is hard-coded in the HTML/template for v1. A later iteration could move
  the team list into a config object, but that is out of scope here.
- **Responsive behavior:** single column throughout; below ~480 px the member role wraps to a second
  line and the nav wraps under the brand. No horizontal scroll at 360 px.

---

## 6. Options considered (for the follow-up build)

The **visual design is fixed** by the wireframes above. The open choice is *how the page would later be
served*. Recorded here so the build ticket starts with the decision made; **no code is written now.**

### Option A — Static file under `src/main/resources/static/about.html` *(recommended)*
Spring Boot serves `src/main/resources/static/**` automatically. Drop the mockup in as
`static/about.html` and it is live at `/about.html` with **zero Java changes**.
- ➕ Simplest possible build; no controller, no dependency, no tests to speak of.
- ➕ Matches the "demo app" spirit and the self-contained mockup 1:1.
- ➖ URL is `/about.html` unless a small redirect/route is added for a clean `/about`.

### Option B — Controller endpoint returning HTML
Add an `AboutController` (or method on `HelloController`) mapping `GET /about` that returns the HTML
string (or a Thymeleaf view).
- ➕ Clean `/about` URL; consistent with the existing `@GetMapping` style.
- ➕ Lets the team list come from server-side data later.
- ➖ Returning a large HTML blob from a `@RestController` string is ugly; doing it "right" means adding
  Thymeleaf (`spring-boot-starter-thymeleaf`) — new dependency for one static page.

### Option C — Templating engine (Thymeleaf) with a proper view
Full `templates/about.html` + view controller.
- ➕ Most "correct" for a real app; easy to template the team list.
- ➖ Over-engineered for a demo: new dependency, view resolver config, more test surface. Not worth it now.

**Recommendation: Option A.** It delivers the exact mockup with no product-code risk and keeps the demo
minimal. If a clean `/about` URL is wanted, add a one-line view-controller redirect in a later ticket —
but `/about.html` is perfectly acceptable for a demo.

---

## 7. Files a follow-up *develop* run would touch (build ticket, not this one)

- `src/main/resources/static/about.html` — **new**, the page itself (Option A).
- *(optional, only for a clean `/about`)* a tiny `WebMvcConfigurer` view-controller or an
  `AboutController` — `src/main/java/com/devagent/devdemo/`.
- *(optional)* a smoke test asserting `GET /about.html` returns 200, alongside the existing
  `HelloControllerTest`.

No changes to `pom.xml` are required for Option A.

---

## 8. Open questions (need a human decision)

1. **URL:** is `/about.html` (Option A, zero code) acceptable, or is a clean `/about` required (adds a
   one-line route)?
2. **Real content:** should the team list and intro use real project/team names, or keep the
   placeholder demo names shown here?
3. **Navigation:** there is no site nav today. Should the header `API`/`About` nav be included now, or
   is a header with just the brand enough for the demo?
4. **Branding:** any brand colour/logo to adopt, or is the neutral single-accent palette in the mockup
   fine to standardize on?

---

## 9. Deliverables in this change
- `design/BRIEF.md` — this document (ASCII wireframes + component hierarchy + rationale). **Primary deliverable.**
- `design/about.html` — self-contained HTML mockup (inline CSS, no scripts) for a richer preview.

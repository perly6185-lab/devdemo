# Design Brief — Contact Page Mockup

**Issue:** [#24 — Design a Contact page mockup for the demo app](https://github.com/perly6185-lab/devdemo/issues/24)
**Milestone:** M2 · **Area:** cross-cutting · **Type:** task · **Priority:** p2 · **Risk:** low
**Role:** Design only — visual mockup first, no implementation, no product-code changes.

---

## 1. Problem

The demo app (**DevAgent Demo**) is currently a bare Spring Boot service: `HelloController`
exposes plain-text REST endpoints (`/hello`, `/goodbye`, `/ping`, `/hola`, `/health`). There is
**no web UI, no CSS, no shared page layout, and no brand styling** anywhere in the repo.

We need a **visual design** for a simple **Contact** page — a header, a short contact form
(name / email / message), and a footer — delivered as:

1. An ASCII wireframe + component hierarchy in this file (`design/BRIEF.md`).
2. A self-contained HTML mockup at `design/contact.html` (inline CSS only, no scripts).

Because there is no existing design language, this brief also establishes a tiny, reusable
visual baseline (colors, spacing, type) that a future develop run can lift into real templates.

### Acceptance criteria (from the issue)

- [ ] `design/BRIEF.md` contains an ASCII wireframe of the Contact page **and** a component hierarchy.
- [ ] `design/contact.html` is a self-contained mockup: **inline CSS only, no scripts, no external resources**.
- [ ] The page shows a **header**, a **short contact form** (name, email, message), and a **footer**.
- [ ] No product code (anything under `src/`) is modified.

---

## 2. Wireframes

### Screen: Contact page — default state (mobile-first, single column)

```
+--------------------------------------+
|  DevAgent Demo         Home  Contact |  <- Header (brand + nav)
+--------------------------------------+
|                                      |
|   Get in touch                       |  <- Page title
|   Questions about the demo? Send us  |     + subtitle
|   a note and we'll reply.            |
|                                      |
|   +------------------------------+   |
|   | Name                         |   |  <- Card wrapping the form
|   | [__________________________] |   |
|   |                              |   |
|   | Email                        |   |
|   | [__________________________] |   |
|   |                              |   |
|   | Message                      |   |
|   | [                          ] |   |
|   | [                          ] |   |  <- textarea (multi-line)
|   | [                          ] |   |
|   |                              |   |
|   |            [  Send message ] |   |  <- primary button (right)
|   +------------------------------+   |
|                                      |
+--------------------------------------+
|  (c) 2026 DevAgent Demo   *  GitHub  |  <- Footer
+--------------------------------------+
```

### State: field-level validation (error)

```
| Email                                |
| [ not-an-email____________________]  |  <- red border
| ! Enter a valid email address        |  <- helper/error text (red)
```

### State: submitted (success confirmation)

```
|   +------------------------------+   |
|   |            (check)           |   |
|   |     Thanks for reaching      |   |
|   |     out! We'll be in touch.  |   |
|   |                              |   |
|   |        [ Send another ]      |   |
|   +------------------------------+   |
```

> Note: error/success states are **design intent only**. The mockup is static (no scripts),
> so these states are documented here for the develop run, not wired up in `contact.html`.

### Wide viewport (>= ~720px)

Same structure, but the form card is centered and capped at ~560px width so line lengths stay
comfortable. Header nav and footer sit edge-to-edge within a max-width content container.
An optional two-column variant is discussed in §4.

---

## 3. Component hierarchy

```
ContactPage
├── SiteHeader
│   ├── Brand ("DevAgent Demo")
│   └── Nav
│       ├── NavLink (Home)
│       └── NavLink (Contact, active)
├── Main
│   ├── PageIntro
│   │   ├── Title ("Get in touch")
│   │   └── Subtitle
│   └── ContactCard
│       └── ContactForm
│           ├── Field (Name)      → label + text input
│           ├── Field (Email)     → label + email input + helper/error slot
│           ├── Field (Message)   → label + textarea
│           └── FormActions
│               └── PrimaryButton ("Send message")
└── SiteFooter
    ├── Copyright
    └── FooterLink (GitHub)
```

New/changed components are all **new** — nothing like this exists in the repo yet.

---

## 4. Options considered

### Option A — Single centered column (card form) — **RECOMMENDED**

One vertical flow: header, intro, a bordered form card centered under a max-width container,
footer. Mobile-first; naturally responsive with no breakpoints required.

- **Pros:** Simplest to build and to later port to a server-rendered template; reads well on
  phone and desktop; least CSS; matches the "demo app" scope.
- **Cons:** Visually plain; doesn't showcase layout range.

### Option B — Two-column split (info panel + form)

Left panel with a short pitch / contact details, right panel with the form; stacks to one
column on narrow screens.

- **Pros:** More polished, "marketing" feel; room for context beside the form.
- **Cons:** Needs a breakpoint and fl/grid layout; more CSS; the demo app has no real contact
  details to fill the left panel, so it risks looking empty.

### Option C — Full-bleed hero + form

Colored hero band with the title, form floating in a card overlapping the hero.

- **Pros:** Most visually striking.
- **Cons:** Overkill for a demo; heavier CSS; fussy to make responsive cleanly.

**Recommendation: Option A.** It satisfies every acceptance criterion with the least complexity,
is trivially responsive, and gives a develop run a clean baseline to translate into a real
template. Option B is a good follow-up once the app has genuine contact content.

---

## 5. Visual baseline (established by this design)

- **Type:** system font stack (`-apple-system, Segoe UI, Roboto, sans-serif`).
- **Palette:** ink `#1f2933` text, muted `#616e7c`, indigo primary `#4f46e5`, surface `#ffffff`,
  page `#f5f7fa`, border `#e4e7eb`, error `#d64545`.
- **Spacing:** 8px scale (8 / 16 / 24 / 32). Content max-width 560px, radius 10px.
- **A11y:** every input has a `<label>` (`for`/`id`), visible focus ring, ~4.5:1 text contrast,
  `<button type="submit">`. Inputs use `type="email"` and `required` for native validation.

---

## 6. Files a develop run would touch

There is no web layer yet, so implementation is greenfield:

- `src/main/resources/static/contact.html` (or a Thymeleaf template + a `GET /contact`
  controller method) — port the mockup into the app.
- Optionally a shared CSS file `src/main/resources/static/css/app.css` if styles move out of
  inline.
- Optionally a `POST /contact` handler + DTO if the form is ever made functional (out of scope
  for this design).
- `design/contact.html` and `design/BRIEF.md` are design artifacts, **not** product code.

---

## 7. Open questions (need a human decision)

1. **Static page vs. Thymeleaf template?** Repo has `spring-boot-starter-web` only (no view
   engine). Static file is zero-dependency; a template needs a dependency + controller.
2. **Should the form actually submit?** This design is presentation-only. A working `POST`
   endpoint, storage/email, spam protection, and server validation are separate follow-ups.
3. **Real contact content?** No address / email / socials exist for the demo — placeholders are
   used. Confirm what (if anything) is real before Option B.
4. **Branding:** "DevAgent Demo" and the indigo accent are invented here. Approve or replace.

---

## 8. Summary

Design a simple, mobile-first **Contact** page for DevAgent Demo using **Option A** (single
centered card form): header with brand + nav, intro title/subtitle, a form card with Name /
Email / Message and a primary "Send message" button, and a footer. Error and success states are
specified for the develop run but not scripted in the static mockup. Deliverables: this brief
(wireframe + hierarchy above) and `design/contact.html` (inline CSS, no scripts). No product
code changed.

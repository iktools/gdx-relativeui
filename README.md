# gdx-relativeui

[![Java CI](https://github.com/iktools/gdx-relativeui/actions/workflows/gradle.yml/badge.svg)](https://github.com/iktools/gdx-relativeui/actions/workflows/gradle.yml)

A relative layout for [LibGDX Scene2d](https://libgdx.com/wiki/graphics/2d/scene2d). Rule based positioning for
direct children of a `RelativeLayout`, so a UI can place actors relative to its parent's edges, center, and other actors without
nested tables or manual math.

> **Status: early, experimental.** The API is minimal and not yet stable — expect breaking changes as the rule
> system grows (sibling-relative rules, anchoring-to-size, etc.).

## Installation

The library targets Maven Central as `io.github.iktools:gdx-relativeui`, published from the `lib` module.

Gradle:

```groovy
implementation "io.github.iktools:gdx-relativeui:1.0.0"
```

Maven:

```xml
<dependency>
  <groupId>io.github.iktools</groupId>
  <artifactId>gdx-relativeui</artifactId>
  <version>1.0.0</version>
</dependency>
```

It requires Scene2d (bundled with the base `com.badlogicgames.gdx:gdx` dependency, which the library already pulls in).

## Usage

Create a `RelativeLayout`, add actors with `add(...)`, and describe where each should sit relative to the layout. All
edge rules return a padding builder; `withPadding(...)` sets the offset from that edge. Call `setSize(...)` or nest the
layout in a parent (e.g. a `Table` with `grow()`) to drive its size.

```java
RelativeLayout layout = new RelativeLayout();

layout.add(new Label("Title", skin)).top().withPadding(20f).centerH();
layout.add(new Label("Move around", skin)).centerH().centerV();
layout.add(new Label("Status", skin)).bottom().withPadding(10f).left();
```

### Rules

| Rule              | Positioning                                                |
|-------------------|------------------------------------------------------------|
| `left()`          | Aligns to the left edge of the parent                      |
| `right()`         | Aligns to the right edge of the parent                     |
| `top()`           | Aligns to the top edge of the parent                       |
| `bottom()`        | Aligns to the bottom edge of the parent                    |
| `centerH()`       | Centers horizontally within the parent                     |
| `centerV()`       | Centers vertically within the parent                       |
| `withPadding(p)`  | Offsets the actor from the edge set by `left/right/top/bottom` |

Rules compose freely, so one actor can be both `top()` and `centerH()`. When multiple rules target the same axis, the
last one wins. `layout()` re-applies every rule on each pass, so actors reposition automatically when the layout or the
stage resizes.

## Demo

`core` and `lwjgl3` make up a small Scene2d demo app for experimenting with the layout — they are scratch/playground
code, not part of the library.

Run it with:

```sh
./gradlew lwjgl3:run
```

## Building and testing

```sh
./gradlew lib:test   # run the library unit tests
./gradlew build      # build everything
```

The library tests use JUnit 5 and Mockito. CI (GitHub Actions) runs `./gradlew lib:test` on every push/PR to `main`.

## License

[Apache License 2.0](LICENSE)

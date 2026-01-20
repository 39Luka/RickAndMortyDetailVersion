# Rick and Morty Character Explorer

An Android app built with **Jetpack Compose** to learn **Retrofit** API consumption and **Coil** image loading using the [Rick and Morty API](https://rickandmortyapi.com/). This project demonstrates modern Android development practices including MVVM architecture, Kotlin Coroutines, REST API integration, and network image loading.

## Project Overview

This app explores the Rick and Morty universe by consuming a RESTful API with visual character data. It's designed as a progressive learning project where features are implemented incrementally to cover essential Retrofit, Coil image loading, and Android development concepts.

**Why This API is Perfect for Learning**:
- Every character includes a high-quality JPEG image URL
- Visual feedback makes API debugging intuitive (images appear when calls work!)
- Real-world scenario: network images + data in a single response
- No authentication complexity - focus on core concepts

## Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose with Material 3
- **Architecture**: MVVM (Model-View-ViewModel)
- **Networking**: Retrofit 3.0.0
- **Serialization**: kotlinx.serialization
- **Image Loading**: Coil for Compose
- **Navigation**: Navigation Compose
- **Concurrency**: Kotlin Coroutines
- **Minimum SDK**: 26 (Android 8.0)
- **Target SDK**: 36

## Dependencies

All required dependencies are already configured in the project:

```kotlin
// Retrofit & Serialization
implementation("com.squareup.retrofit2:retrofit:3.0.0")
implementation("com.squareup.retrofit2:converter-kotlinx-serialization:3.0.0")
implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")

// Image Loading
implementation("io.coil-kt:coil-compose:2.7.0")

// MVVM & Navigation
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.10.0")
implementation("androidx.navigation:navigation-compose:2.9.6")

// Compose BOM and UI
implementation(platform("androidx.compose:compose-bom:2026.01.00"))
implementation("androidx.compose.material3:material3")
// ... other Compose dependencies
```

## API Information

**Base URL**: `https://rickandmortyapi.com/api/`

**Available Endpoints**:
- `/character` - 826 characters with pagination (20 per page)
- `/location` - 126 locations across dimensions
- `/episode` - 51 episodes from the series

**Features**:
- No authentication required
- Pagination support
- Query filters (name, status, species, gender, etc.)
- Returns JSON with character images

[Full API Documentation](https://rickandmortyapi.com/documentation)

## Image Loading with Coil

One of the key learning aspects of this project is **network image loading**. Every character in the API includes an `image` field with a direct URL to their avatar:

```json
{
  "id": 1,
  "name": "Rick Sanchez",
  "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
}
```

### Why Coil?

- **Compose-First**: Built specifically for Jetpack Compose with `AsyncImage` composable
- **Kotlin Coroutines**: Seamless integration with suspend functions
- **Automatic Caching**: Memory + disk caching out of the box (no configuration needed)
- **Lightweight**: Smaller than Glide/Picasso
- **Modern**: Uses OkHttp under the hood (same as Retrofit)

### Basic Usage

```kotlin
import coil.compose.AsyncImage

@Composable
fun CharacterCard(character: Character) {
    AsyncImage(
        model = character.image,  // URL from API
        contentDescription = character.name,
        modifier = Modifier.size(80.dp)
    )
}
```

### Advanced Usage (with Loading States)

```kotlin
import coil.compose.AsyncImage
import androidx.compose.ui.res.painterResource

AsyncImage(
    model = character.image,
    contentDescription = character.name,
    placeholder = painterResource(R.drawable.placeholder),  // While loading
    error = painterResource(R.drawable.error_image),        // If fails
    contentScale = ContentScale.Crop,
    modifier = Modifier
        .size(100.dp)
        .clip(CircleShape)
)
```

### What Students Learn

1. **Network Image Loading**: Loading images from URLs (not local resources)
2. **Async Operations**: Understanding how images load asynchronously
3. **Caching Strategies**: How Coil caches images automatically
4. **Error Handling**: Placeholder and error states for better UX
5. **Performance**: Lazy loading in scrollable lists
6. **Accessibility**: Proper content descriptions for images

## Learning Phases

This project is designed to be built incrementally across multiple lessons:

### Phase 1: Basic GET Requests + Image Loading
**Goal**: Display a list of characters with their images

**Learning Objectives**:
- Set up Retrofit instance and API interface
- Create data models with `@Serializable`
- Make simple GET requests to `/character`
- Parse JSON responses (including image URLs)
- Display data in a LazyColumn with Compose
- **Load character images from URLs using Coil's AsyncImage**
- Understand the difference between local and network images

**Key Concepts**: `@GET`, Retrofit.Builder, `baseUrl()`, JSON deserialization, `AsyncImage`, network image URLs

**UI Components**:
- LazyColumn with character cards
- Each card shows: Avatar image (from API), Name, Status, Species

---

### Phase 2: Pagination
**Goal**: Implement "Load More" functionality

**Learning Objectives**:
- Handle API pagination metadata (info.next, info.prev)
- Implement infinite scroll or "Load More" button
- Manage loading states in ViewModel
- **Observe Coil's automatic image caching** (previously loaded images appear instantly)
- Understand lazy loading performance with images

**Key Concepts**: API response structure, state management, list concatenation, image caching

---

### Phase 3: Search & Filters
**Goal**: Add search bar and filter options

**Learning Objectives**:
- Use query parameters with `@Query`
- Implement search functionality (filter by name)
- Add filter chips (status: alive/dead, species, gender)
- Handle empty results gracefully

**Key Concepts**: `@Query`, dynamic parameters, query building

---

### Phase 4: Detail Views
**Goal**: Show detailed character information with hero image

**Learning Objectives**:
- Use path parameters with `@Path`
- Navigate to detail screen with Navigation Compose
- Display character's origin location
- Show episodes where character appears
- Make multiple related API calls
- **Use larger image with placeholder/error states** (showcase advanced Coil features)
- Apply different image styles (CircleShape, rounded corners, etc.)

**Key Concepts**: `@Path`, navigation arguments, related data fetching, AsyncImage with states

**UI Enhancements**:
- Hero image at top of detail screen
- Placeholder while loading
- Error image if network fails

---

### Phase 5: Multiple Endpoints
**Goal**: Explore Locations and Episodes

**Learning Objectives**:
- Organize multiple API endpoints in service interface
- Implement bottom navigation or tabs
- Reuse UI components for different data types
- Apply consistent patterns across endpoints

**Key Concepts**: Code organization, reusability, API interface design

---

### Phase 6: Error Handling (Optional Advanced)
**Goal**: Handle errors gracefully

**Learning Objectives**:
- Catch network errors and HTTP errors
- Display error states in UI
- Implement retry mechanisms
- Handle no internet connection

**Key Concepts**: Try-catch blocks, `Response<T>`, error UI states

## Architecture

The app follows **MVVM architecture** with a Repository pattern:

```
├── data/
│   ├── model/           # Data classes (@Serializable)
│   ├── remote/          # Retrofit API interface
│   └── repository/      # Repository (Singleton)
├── ui/
│   ├── screens/         # Composable screens
│   └── viewmodel/       # ViewModels
└── MainActivity.kt
```

### Repository Pattern (Singleton)

For simplicity, this project uses a **singleton Repository**. While not production-best-practice, it's acceptable for learning purposes and keeps the focus on Retrofit:

```kotlin
object CharacterRepository {
    private val api: RickAndMortyApi = /* Retrofit instance */

    suspend fun getCharacters(): List<Character> {
        return api.getCharacters()
    }
}
```

> **Note**: In production apps, use Dependency Injection (Hilt/Koin) instead of singletons for better testability and flexibility.

## Project Structure Example

```
net.iessochoa.sergiocontreras.rickandmortyapi/
├── data/
│   ├── model/
│   │   ├── Character.kt
│   │   ├── CharacterResponse.kt
│   │   ├── Location.kt
│   │   └── Episode.kt
│   ├── remote/
│   │   └── RickAndMortyApi.kt
│   └── repository/
│       └── CharacterRepository.kt
├── ui/
│   ├── screens/
│   │   ├── CharacterListScreen.kt
│   │   └── CharacterDetailScreen.kt
│   ├── viewmodel/
│   │   └── CharacterViewModel.kt
│   └── theme/
│       ├── Color.kt
│       ├── Theme.kt
│       └── Type.kt
└── MainActivity.kt
```

## Sample API Response

**GET** `https://rickandmortyapi.com/api/character`

```json
{
  "info": {
    "count": 826,
    "pages": 42,
    "next": "https://rickandmortyapi.com/api/character?page=2",
    "prev": null
  },
  "results": [
    {
      "id": 1,
      "name": "Rick Sanchez",
      "status": "Alive",
      "species": "Human",
      "gender": "Male",
      "origin": {
        "name": "Earth (C-137)",
        "url": "https://rickandmortyapi.com/api/location/1"
      },
      "location": {
        "name": "Citadel of Ricks",
        "url": "https://rickandmortyapi.com/api/location/3"
      },
      "image": "https://rickandmortyapi.com/api/character/avatar/1.jpeg",  // ⭐ Image URL for Coil
      "episode": [
        "https://rickandmortyapi.com/api/episode/1",
        "https://rickandmortyapi.com/api/episode/2"
      ]
    }
  ]
}
```

**Key Fields for This Project**:
- `image`: Direct URL to character avatar (300x300 JPEG) - **used with Coil's AsyncImage**
- `name`, `status`, `species`: Text data for UI
- `info.next`: Pagination URL for loading more characters
- `origin` & `location`: Nested objects with related data

## Getting Started

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/RickAndMortyApi.git
   cd RickAndMortyApi
   ```

2. **Open in Android Studio**
   - Android Studio Ladybug or later
   - Ensure Gradle sync completes successfully

3. **Run the app**
   - All dependencies are pre-configured
   - No API key required
   - Connect a device or start an emulator
   - Click Run

## Learning Resources

- [Retrofit Documentation](https://square.github.io/retrofit/)
- [Coil Documentation](https://coil-kt.github.io/coil/compose/)
- [kotlinx.serialization Guide](https://github.com/Kotlin/kotlinx.serialization)
- [Jetpack Compose Basics](https://developer.android.com/jetpack/compose)
- [Rick and Morty API Docs](https://rickandmortyapi.com/documentation)

## Quick Reference

### Key Retrofit Annotations

| Annotation | Purpose | Example |
|------------|---------|---------|
| `@GET` | HTTP GET request | `@GET("character")` |
| `@Query` | Query parameter | `@Query("name") name: String` |
| `@Path` | URL path segment | `@GET("character/{id}")` |
| `@POST` | HTTP POST request | `@POST("user")` |
| `@Body` | Request body | `@Body user: User` |

### Key Coil AsyncImage Parameters

| Parameter | Purpose | Example |
|-----------|---------|---------|
| `model` | Image URL or resource | `model = character.image` |
| `contentDescription` | Accessibility label | `contentDescription = "Rick's avatar"` |
| `placeholder` | Shown while loading | `placeholder = painterResource(R.drawable.loading)` |
| `error` | Shown on failure | `error = painterResource(R.drawable.error)` |
| `contentScale` | How image fits | `contentScale = ContentScale.Crop` |
| `modifier` | Compose modifiers | `modifier = Modifier.size(100.dp)` |

## Common Challenges & Solutions

### Challenge 1: JSON Parsing Errors
**Problem**: App crashes with serialization errors
**Solution**: Ensure data classes match API response structure exactly. Use `@SerialName` for different naming conventions.
```kotlin
@Serializable
data class Character(
    val id: Int,
    val name: String,
    val image: String  // Must match API field name exactly
)
```

### Challenge 2: UI Thread Network Calls
**Problem**: NetworkOnMainThreadException
**Solution**: Always use `suspend` functions with coroutines in ViewModels (`viewModelScope.launch`).

### Challenge 3: Images Not Loading
**Problem**: Images don't appear or show broken image icons
**Solutions**:
- ✅ Ensure internet permission in AndroidManifest.xml: `<uses-permission android:name="android.permission.INTERNET"/>`
- ✅ Verify image URLs are included in data model
- ✅ Add placeholder to see loading state: `placeholder = painterResource(R.drawable.placeholder)`
- ✅ Add error state to debug: `error = painterResource(R.drawable.error)`
- ✅ Check Logcat for Coil error messages

**Example with debugging states**:
```kotlin
AsyncImage(
    model = character.image,
    contentDescription = character.name,
    placeholder = painterResource(R.drawable.placeholder),  // Shows while loading
    error = painterResource(R.drawable.error),              // Shows if failed
    onError = { Log.e("Coil", "Failed to load: ${character.image}") }
)
```

### Challenge 4: Slow Image Loading in Lists
**Problem**: Images load slowly when scrolling
**Solution**: Coil automatically handles this! Its memory/disk caching makes subsequent loads instant. If still slow, check network connection or consider using lower resolution images.

### Challenge 5: Pagination State Management
**Problem**: Duplicate items or lost data when loading more pages
**Solution**: Track current page and "next" URL in ViewModel state. Append results to existing list, don't replace.

## License

This project is for educational purposes. The Rick and Morty API is public and free to use.

---

**Built for learning Retrofit by**: [Your Institution Name]
**API Provider**: [rickandmortyapi.com](https://rickandmortyapi.com/)

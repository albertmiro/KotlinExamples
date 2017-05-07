package com.albertmiro.myplayer.examples

/*
//Ejemplos con el MediaProvider fetchMedia

MediaProvider.fetchMediaAsync {
    items -> adapter.items = items
}

MediaProvider.fetchMediaAsync { items ->
    adapter.items = when(filter) {
        Filter.None -> items
        is Filter.ByType -> items.filter { it.type == filter.type }
    }
}

MediaProvider.fetchMediaAsync {
    items -> when (item.itemId) {
        R.id.filter_photos -> items.filter { it.type == MediaItem.Type.PHOTO }
        R.id.filter_videos -> items.filter { it.type == MediaItem.Type.VIDEO }
        else -> this
    }
}

adapter.items = with(MediaProvider.fetchMedia()) {
    when (item.itemId) {
        R.id.filter_photos -> filter { it.type == MediaItem.Type.PHOTO }
        R.id.filter_videos -> filter { it.type == MediaItem.Type.VIDEO }
        else -> this
    }
}

*/
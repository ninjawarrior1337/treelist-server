package treelist.server

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.jaudiotagger.audio.AudioFileIO
import org.jaudiotagger.tag.FieldKey
import org.jaudiotagger.tag.images.Artwork
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*

data class AudioFile(@SerializedName("path") val path: String)
{
    var pathObj: Path = Paths.get(path)

    private var audioFile = AudioFileIO.read(pathObj.toFile())

    @SerializedName("title")
    var title = ""

    @SerializedName("artist")
    var artist = ""

    @SerializedName("album")
    var album = ""

    @SerializedName("samples")
    var samples = ""

    @SerializedName("genre")
    var genre = ""

    @SerializedName("hash")
    var hash = hashCode().toString()

    val artwork: ByteArray?
        get() = audioFile.tag?.firstArtwork?.binaryData

    init {
        parseMetadata()
    }

    private fun parseMetadata()
    {
        title =
            if(!audioFile.tag?.getFirst(FieldKey.TITLE).isNullOrEmpty())
                audioFile.tag.getFirst(FieldKey.TITLE)
            else
                pathObj.fileName.toFile().nameWithoutExtension
        if(audioFile.tag != null)
        {
            artist = audioFile.tag.getFirst(FieldKey.ARTIST).orEmpty()
            album = audioFile.tag.getFirst(FieldKey.ALBUM).orEmpty()
            samples = audioFile.audioHeader.sampleRate
            genre = audioFile.tag.getFirst(FieldKey.GENRE).orEmpty()
        }
    }

    override fun toString(): String {
        return Gson().toJson(mapOf(
            "hash" to hash,
            "path" to path,
            "title" to title,
            "artist" to artist,
            "album" to album,
            "samples" to samples,
            "genre" to genre
        ))
//        return Gson().toJson(metadata)
    }
}
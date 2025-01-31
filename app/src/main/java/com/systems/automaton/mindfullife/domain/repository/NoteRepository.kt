package com.systems.automaton.mindfullife.domain.repository

import com.systems.automaton.mindfullife.domain.model.Note
import com.systems.automaton.mindfullife.domain.model.NoteFolder
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getAllNotes(): Flow<List<Note>>

    suspend fun getNote(id: Int): Note

    suspend fun searchNotes(query: String): List<Note>

    fun getNotesByFolder(folderId: Int): Flow<List<Note>>

    suspend fun addNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun insertNoteFolder(folder: NoteFolder)

    suspend fun updateNoteFolder(folder: NoteFolder)

    suspend fun deleteNoteFolder(folder: NoteFolder)

    fun getAllNoteFolders(): Flow<List<NoteFolder>>

}
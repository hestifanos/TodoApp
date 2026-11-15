package com.example.todo

internal fun buildTaskSummary(title: String, deadline: String, description: String): String? {
    val cleanTitle = title.trim()
    if (cleanTitle.isEmpty()) return null

    val lines = mutableListOf(cleanTitle)
    val cleanDeadline = deadline.trim()
    val cleanDescription = description.trim()
    if (cleanDeadline.isNotEmpty()) lines.add("Due: $cleanDeadline")
    if (cleanDescription.isNotEmpty()) lines.add(cleanDescription)
    return lines.joinToString("\n")
}

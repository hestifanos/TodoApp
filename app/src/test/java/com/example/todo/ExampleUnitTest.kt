package com.example.todo

import org.junit.Test

import org.junit.Assert.*

class ExampleUnitTest {
    @Test
    fun createsTrimmedTaskSummaryWithOptionalDetails() {
        assertEquals(
            "Plan launch\nDue: Friday\nReview final checklist",
            buildTaskSummary(" Plan launch ", " Friday ", " Review final checklist ")
        )
    }

    @Test
    fun rejectsBlankTitleAndOmitsEmptyDetails() {
        assertNull(buildTaskSummary("  ", "", ""))
        assertEquals("Plan launch", buildTaskSummary("Plan launch", "", ""))
    }
}
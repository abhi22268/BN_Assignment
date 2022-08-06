package com.example.bn_android_assignment.pojo


data class CardsResponse(
    val cards: List<Card>?
)

data class Card(
    val data: Data?
)

data class Data(
    val horizontal_cards: List<HorizontalCard>?,
    val main_post: MainPost?,
    val total_matches_count: Int?
)

data class HorizontalCard(
    val assigned_to: AssignedTo?,
    val info: String?,
    val post_uuid: String?,
    val price: Int?,
    val rent_expected: Int?,
    val sub_info: List<SubInfo>?,
    val title: String?,
    val type: Type?,
    val updation_time: Int?,
    val uuid: String?
)

data class MainPost(
    val assigned_to: AssignedTo?,
    val info: String?,
    val match_count: Int?,
    val max_budget: Int?,
    val max_rent: Int?,
    val post_uuid: String?,
    val sub_info: List<SubInfo>?,
    val title: String?,
    val type: Type?,
    val uuid: String?
)

data class AssignedTo(
    val name: String?,
    val org_name: String?,
    val phone_number: String?,
    val profile_pic_url: String?,
    val uuid: String?
)

data class SubInfo(
    val perfect_match: Boolean?,
    val text: String?
)

data class Type(
    val id: String?,
    val name: String?
)


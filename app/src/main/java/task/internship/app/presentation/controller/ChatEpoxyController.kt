package task.internship.app.presentation.controller

import com.airbnb.epoxy.EpoxyController
import task.internship.app.R
import task.internship.app.databinding.ModelMessageBinding
import task.internship.app.databinding.ModelParticipantInfoBinding
import task.internship.app.presentation.helper.TypeDataClass
import task.internship.app.presentation.helper.ViewBindingKotlinModel

class ChatEpoxyController : EpoxyController() {
    private val data = mutableListOf<TypeDataClass>()

    override fun buildModels() {

        data.forEachIndexed { idx, it ->
            if (it.isTypeMessage) {
                MessageEpoxyModel(
                    it.username,
                    it.message!!
                ).id(idx)
                    .addTo(this)

            } else {
                ParticipantInfoEpoxyModel(
                    it.username,
                    it.hasJoined,
                    it.totalParticipants
                ).id(idx).addTo(this)
            }
        }
    }

    fun addItem(typeDataClass: TypeDataClass) {
        data.add(typeDataClass)
        requestModelBuild()
    }
}

data class MessageEpoxyModel(
    val username: String,
    val message: String
) : ViewBindingKotlinModel<ModelMessageBinding>(R.layout.model_message) {

    override fun ModelMessageBinding.bind() {
        userNameTextview.text = username
        messageTextview.text = message
    }
}

data class ParticipantInfoEpoxyModel(
    val username: String,
    val hasJoined: Boolean?,
    val totalParticipants: Int?
) : ViewBindingKotlinModel<ModelParticipantInfoBinding>(R.layout.model_participant_info) {

    override fun ModelParticipantInfoBinding.bind() {
        hasJoined?.let {
            val text = username + if (hasJoined) " Joined" else " Left"

            userJoinLeaveTextview.text = text
            totalParticipantsTextview.text = "Total participants:- $totalParticipants"

        } ?: run {
            userJoinLeaveTextview.text = "$username is typing"
            totalParticipantsTextview.text = null
        }
    }
}

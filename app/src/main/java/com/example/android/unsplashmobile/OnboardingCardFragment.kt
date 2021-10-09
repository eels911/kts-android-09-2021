package com.example.android.unsplashmobile

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_onboarding_card.*
import java.io.IOException

class OnboardingCardFragment : Fragment(R.layout.fragment_onboarding_card) {
    companion object {
        const val ARG_POSITION = "position"

        fun getInstance(position: Int): Fragment {
            val onboardingCard = OnboardingCardFragment()
            val bundle = Bundle()
            bundle.putInt(ARG_POSITION, position)
            onboardingCard.arguments = bundle
            return onboardingCard
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val position = requireArguments().getInt(ARG_POSITION)
        val imageFilePath = getString(R.string.onboard_image_path, position)
        val onboardNamesArray = requireContext().resources.getStringArray(R.array.onboard_names)

        setImageFromAssetsFile(requireContext(), imageFilePath)
        tv_onboardingName.text = onboardNamesArray[position]

        if (position == onboardNamesArray.size - 1) {
            bt_onLoginFrag.visibility = View.VISIBLE
            bt_onLoginFrag.setOnClickListener {
                val action = OnboardingFragmentDirections.actionOnboardingFragmentToLoginFragment()
                findNavController().navigate(action)
            }
        }
    }

    /**
     * Gets the file from assets, converts it into a bitmap and sets it on the ImageView
     * @param context a Context instance
     * @param filePath relative path of the file
     */
    private fun setImageFromAssetsFile(context: Context, filePath: String) {
        val imageBitmap: Bitmap?
        val assets = context.resources.assets
        try {
            val fileStream = assets.open(filePath)
            imageBitmap = BitmapFactory.decodeStream(fileStream)
            fileStream.close()
            iv_onboarding.setImageBitmap(imageBitmap)
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(context, getString(R.string.image_loading_error), Toast.LENGTH_SHORT).show()
        }
    }
}

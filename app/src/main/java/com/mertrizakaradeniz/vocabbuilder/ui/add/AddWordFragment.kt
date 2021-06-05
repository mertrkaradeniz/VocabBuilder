package com.mertrizakaradeniz.vocabbuilder.ui.add

import android.Manifest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mertrizakaradeniz.vocabbuilder.R
import com.mertrizakaradeniz.vocabbuilder.data.model.Word
import com.mertrizakaradeniz.vocabbuilder.databinding.FragmentAddWordBinding
import com.mertrizakaradeniz.vocabbuilder.ui.list.WordViewModel
import com.mertrizakaradeniz.vocabbuilder.utils.Constant.PERMISSION_EXTERNAL_STORAGE_REQUEST_CODE
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddWordFragment : Fragment(R.layout.fragment_add_word), EasyPermissions.PermissionCallbacks {

    private var _binding: FragmentAddWordBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WordViewModel by viewModels()
    private lateinit var word: Word

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleClickEvent()
    }

    private fun handleClickEvent() {
        binding.imgQuestion.setOnClickListener {
            requestStoragePermission()
        }
        binding.btnAdd.setOnClickListener {
            binding.apply {
                val name = etWord.text.toString()
                val definition = etDefinition.text.toString()
                val sentence = etSentence.text.toString()
                if (name.isNotEmpty() && definition.isNotEmpty() && sentence.isNotEmpty() && spCategories.selectedItemPosition > 0) {
                    word = Word(
                        name,
                        spCategories.selectedItem.toString(),
                        definition,
                        sentence,
                        etSynonyms.text.toString(),
                        etAntonyms.text.toString()
                    )
                    viewModel.upsertWord(word)
                    Toast.makeText(
                        requireContext(),
                        "Word is saved successfully.",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(AddWordFragmentDirections.actionAddWordFragmentToWordListFragment())
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Categories, name, definition and sentence cannot be empty.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun hasLStoragePermission() =
        EasyPermissions.hasPermissions(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

    private fun requestStoragePermission() {
        EasyPermissions.requestPermissions(
            this,
            "You cannot choose image without Storage Permission.",
            PERMISSION_EXTERNAL_STORAGE_REQUEST_CODE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, listOf(perms.first()))) {
            SettingsDialog.Builder(requireActivity()).build().show()
        } else {
            requestStoragePermission()
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        Toast.makeText(
            requireContext(),
            "Permission Granted!",
            Toast.LENGTH_SHORT
        ).show()
        setViewVisibility()
    }

    private fun setViewVisibility() {
        binding.btnAdd.isEnabled = hasLStoragePermission()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
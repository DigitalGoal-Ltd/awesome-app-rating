package com.suddenh4x.ratingdialog

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.suddenh4x.ratingdialog.buttons.ConfirmButtonClickListener
import com.suddenh4x.ratingdialog.buttons.CustomFeedbackButtonClickListener
import com.suddenh4x.ratingdialog.buttons.RateButton
import com.suddenh4x.ratingdialog.buttons.RateDialogClickListener
import com.suddenh4x.ratingdialog.dialog.DialogOptions
import com.suddenh4x.ratingdialog.dialog.RateDialogFragment
import com.suddenh4x.ratingdialog.logging.RatingLogger
import com.suddenh4x.ratingdialog.preferences.ConditionsChecker
import com.suddenh4x.ratingdialog.preferences.MailSettings
import com.suddenh4x.ratingdialog.preferences.PreferenceUtil
import com.suddenh4x.ratingdialog.preferences.RatingThreshold

object AppRating {

    fun reset(context: Context) {
        PreferenceUtil.reset(context)
        RatingLogger.warn("Settings were reset.")
    }

    data class Builder(var activity: AppCompatActivity) {
        private var dialogOptions: DialogOptions = DialogOptions
        internal var isDebug = false

        fun setIconDrawable(iconDrawable: Drawable?) = apply {
            dialogOptions.iconDrawable = iconDrawable
            RatingLogger.debug("Use custom icon drawable.")
        }

        fun setRateLaterButtonTextId(@StringRes rateLaterButtonTextId: Int) = apply {
            dialogOptions.rateLaterButton.textId = rateLaterButtonTextId
        }

        fun setRateLaterButtonClickListener(rateLaterButtonClickListener: RateDialogClickListener) =
            apply {
                dialogOptions.rateLaterButton.rateDialogClickListener =
                    rateLaterButtonClickListener
            }

        fun showRateNeverButton(
            @StringRes rateNeverButtonTextId: Int = R.string.rating_dialog_button_rate_never,
            rateNeverButtonClickListener: RateDialogClickListener? = null
        ) = apply {
            dialogOptions.rateNeverButton =
                RateButton(rateNeverButtonTextId, rateNeverButtonClickListener)
            RatingLogger.debug("Show rate never button.")
        }

        // rating dialog overview
        fun setTitleTextId(@StringRes titleTextId: Int) = apply {
            dialogOptions.titleTextId = titleTextId
        }

        fun setMessageTextId(@StringRes messageTextId: Int) = apply {
            dialogOptions.messageTextId = messageTextId
        }

        fun setConfirmButtonTextId(@StringRes confirmButtonTextId: Int) = apply {
            dialogOptions.confirmButton.textId = confirmButtonTextId
        }

        fun setConfirmButtonClickListener(confirmButtonClickListener: ConfirmButtonClickListener) =
            apply {
                dialogOptions.confirmButton.confirmButtonClickListener = confirmButtonClickListener
            }

        fun setShowOnlyFullStars(showOnlyFullStars: Boolean) = apply {
            dialogOptions.showOnlyFullStars = showOnlyFullStars
        }

        // rating dialog store
        fun setStoreRatingTitleTextId(@StringRes storeRatingTitleTextId: Int) = apply {
            dialogOptions.storeRatingTitleTextId = storeRatingTitleTextId
        }

        fun setStoreRatingMessageTextId(@StringRes storeRatingMessageTextId: Int) = apply {
            dialogOptions.storeRatingMessageTextId = storeRatingMessageTextId
        }

        fun setRateNowButtonTextId(@StringRes rateNowButtonTextId: Int) = apply {
            dialogOptions.rateNowButton.textId = rateNowButtonTextId
        }

        fun overwriteRateNowButtonClickListener(rateNowButtonClickListener: RateDialogClickListener) =
            apply {
                dialogOptions.rateNowButton.rateDialogClickListener = rateNowButtonClickListener
            }

        fun setAdditionalRateNowButtonClickListener(additionalRateNowButtonClickListener: RateDialogClickListener) =
            apply {
                dialogOptions.additionalRateNowButtonClickListener =
                    additionalRateNowButtonClickListener
            }

        // rating dialog feedback
        fun setFeedbackTitleTextId(@StringRes feedbackTitleTextId: Int) = apply {
            dialogOptions.feedbackTitleTextId = feedbackTitleTextId
        }

        fun setNoFeedbackButtonTextId(@StringRes noFeedbackButtonTextId: Int) = apply {
            dialogOptions.noFeedbackButton.textId = noFeedbackButtonTextId
        }

        fun setNoFeedbackButtonClickListener(noFeedbackButtonClickListener: RateDialogClickListener) =
            apply {
                dialogOptions.noFeedbackButton.rateDialogClickListener =
                    noFeedbackButtonClickListener
            }

        // rating dialog mail feedback
        fun setMailFeedbackMessageTextId(@StringRes feedbackMailMessageTextId: Int) = apply {
            dialogOptions.mailFeedbackMessageTextId = feedbackMailMessageTextId
        }

        fun setMailSettingsForFeedbackDialog(mailSettings: MailSettings) = apply {
            dialogOptions.mailSettings = mailSettings
        }

        fun setMailFeedbackButtonTextId(@StringRes mailFeedbackButtonTextId: Int) = apply {
            dialogOptions.mailFeedbackButton.textId = mailFeedbackButtonTextId
        }

        fun overwriteMailFeedbackButtonClickListener(mailFeedbackButtonClickListener: RateDialogClickListener) =
            apply {
                dialogOptions.mailFeedbackButton.rateDialogClickListener =
                    mailFeedbackButtonClickListener
            }

        fun setAdditionalMailFeedbackButtonClickListener(additionalMailFeedbackButtonClickListener: RateDialogClickListener) =
            apply {
                dialogOptions.additionalMailFeedbackButtonClickListener =
                    additionalMailFeedbackButtonClickListener
            }

        // rating dialog custom feedback
        fun setUseCustomFeedback(useCustomFeedback: Boolean) = apply {
            dialogOptions.useCustomFeedback = useCustomFeedback
            RatingLogger.debug("Use custom feedback instead of mail feedback: $useCustomFeedback.")
        }

        fun setCustomFeedbackMessageTextId(@StringRes feedbackCustomMessageTextId: Int) = apply {
            dialogOptions.customFeedbackMessageTextId = feedbackCustomMessageTextId
        }

        fun setCustomFeedbackButtonTextId(@StringRes customFeedbackButtonTextId: Int) = apply {
            dialogOptions.customFeedbackButton.textId = customFeedbackButtonTextId
        }

        fun setCustomFeedbackButtonClickListener(customFeedbackButtonClickListener: CustomFeedbackButtonClickListener) =
            apply {
                dialogOptions.customFeedbackButton.customFeedbackButtonClickListener =
                    customFeedbackButtonClickListener
            }

        // other settings
        fun setRatingThreshold(ratingThreshold: RatingThreshold) = apply {
            dialogOptions.ratingThreshold = ratingThreshold
            RatingLogger.debug("Set rating threshold to ${ratingThreshold.ordinal / 2}.")
        }

        fun setCancelable(cancelable: Boolean) = apply {
            dialogOptions.cancelable = cancelable
            RatingLogger.debug("Set cancelable to $cancelable.")
        }

        fun setMinimumLaunchTimes(launchTimes: Int) = apply {
            PreferenceUtil.setMinimumLaunchTimes(activity, launchTimes)
        }

        fun setMinimumLaunchTimesToShowAgain(launchTimesToShowAgain: Int) = apply {
            PreferenceUtil.setMinimumLaunchTimesToShowAgain(
                activity,
                launchTimesToShowAgain
            )
        }

        fun setMinimumDays(minimumDays: Int) = apply {
            PreferenceUtil.setMinimumDays(activity, minimumDays)
        }

        fun setMinimumDaysToShowAgain(minimumDaysToShowAgain: Int) = apply {
            PreferenceUtil.setMinimumDaysToShowAgain(activity, minimumDaysToShowAgain)
        }

        fun setLoggingEnabled(isLoggingEnabled: Boolean) = apply {
            RatingLogger.isLoggingEnabled = isLoggingEnabled
        }

        fun setDebug(isDebug: Boolean) = apply {
            this.isDebug = isDebug
            RatingLogger.warn("Set debug to $isDebug. Don't use this for production.")
        }

        fun create(): DialogFragment = RateDialogFragment()

        fun showNow() {
            val rateDialogFragment = RateDialogFragment()
            rateDialogFragment.show(activity.supportFragmentManager, TAG)
        }

        fun showIfMeetsConditions() {
            PreferenceUtil.increaseLaunchTimes(activity)
            if (isDebug || ConditionsChecker.shouldShowDialog(activity)) {
                RatingLogger.info("Show rating dialog now: Conditions met.")
                showNow()
            } else {
                RatingLogger.info("Don't show rating dialog: Conditions not met.")
            }
        }

        companion object {
            private val TAG = AppRating::class.java.simpleName
        }
    }
}

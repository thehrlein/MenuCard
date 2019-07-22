// The Cloud Functions for Firebase SDK to create Cloud Functions and setup triggers.
const functions = require('firebase-functions');

// The Firebase Admin SDK to access the Cloud Firestore.
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);

exports.sendNotification = functions.firestore.document("orders/{userEmail}").onUpdate((change, context) => {

	const before = change.before.data();
	const first = before[Object.keys(before)[0]];
	const status = first.status;
	const userEmail = first.fireId;

	admin.firestore().collection("users").doc(userEmail).get().then(queryResult => {

		var firebaseTokens = queryResult.data().firebaseToken;
		var notificationContent = {
				notification: {
					title: "Status updated -> " + status,
					body: status,
					icon: "default",
					sound: "default"
				} 
			}

		const data = {
			android: {
				notification: {
					title: "Status updated -> " + status,
					body: status,
					icon: "default",
					sound: "default"
				}			
			},
			tokens: firebaseTokens
		}

		sendNotification(data, firebaseTokens);
		return true;
	})
	.catch((error) => {
		console.log("Notification error!");
		console.log(error);
		return false;
	});

	return true;
});

function sendNotification(message, toks) {
	console.log(message);
	admin.messaging().sendMulticast(message)
		.then((response => {
			console.log(response);
			console.log(response.successCount + ' messages were sent successfully!');
			return true;
		}))
		.catch((error => {
			console.log("Error sending messages = " + error);
			return false;
		}));
}
/*********************************************************************
 Copyright 2014 the Flapi authors

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ********************************************************************/

package unquietcode.tools.flapi.plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
* @author Ben Fagin
* @version 04-29-2012
*/
public class EmailMessage {
	private String subject;
	private String sender;
	private List<String> recipients = new ArrayList<String>();
	private List<String> cc = new ArrayList<String>();
	private List<String> bcc = new ArrayList<String>();
	private String body;
	private List<File> attachments = new ArrayList<File>();

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<String> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}

	public List<String> getCc() {
		return cc;
	}

	public void setCc(List<String> cc) {
		this.cc = cc;
	}

	public List<String> getBcc() {
		return bcc;
	}

	public void setBcc(List<String> bcc) {
		this.bcc = bcc;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public List<File> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<File> attachments) {
		this.attachments = attachments;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		sb.append("-----------------------------------------------------\n");

		sb.append("To: ");
		for (String recipient : recipients) {
			if (!first) { sb.append(", "); }
			else { first = false; }
			sb.append(recipient);
		}

		sb.append("\nFrom: ").append(sender);

		if (!cc.isEmpty()) {
			first = true;

			sb.append("\nCC: ");
			for (String recipient : cc) {
				if (!first) { sb.append(", "); }
				else { first = false; }
				sb.append(recipient);
			}
		}

		if (!bcc.isEmpty()) {
			first = true;

			sb.append("\nBCC: ");
			for (String recipient : bcc) {
				if (!first) { sb.append(", "); }
				else { first = false; }
				sb.append(recipient);
			}
		}

		sb.append("\nSubject: ").append(subject)
		  .append("\n\n").append(splitBody(body))
		  .append("\n-----------------------------------------------------");

		return sb.toString();
	}

	/*
		From http://stackoverflow.com/questions/7528045/large-string-split-into-lines-with-maximum-length-in-java
	 */
	private static String splitBody(String body) {
		StringTokenizer tok = new StringTokenizer(body, " ");
		StringBuilder output = new StringBuilder(body.length());
		int lineLen = 0;

		while (tok.hasMoreTokens()) {
			String word = tok.nextToken();

			if (lineLen + word.length() > 80) {
				output.append("\n");
				lineLen = 0;
			}
			output.append(word).append(" ");
			lineLen += word.length();
		}

		return output.toString();
	}

/*	private static String splitBody(String body) {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		int start = 0;
		int end = start + 120;

		while (end <= body.length()) {
			if (!first) { sb.append("\n"); }
			else { first = false; }

			sb.append(body.substring(start, end));
			if (end == body.length()) { break; }

			start = end;
			end = start + 120;
			if (end > body.length()) { end = body.length(); }
		}

		return sb.toString();
	}*/
}

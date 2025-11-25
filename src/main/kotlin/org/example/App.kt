package org.example

import kotlinx.coroutines.*
import ai.koog.prompt.executor.clients.deepseek.DeepSeekLLMClient
import ai.koog.prompt.executor.clients.deepseek.DeepSeekModels
import ai.koog.prompt.executor.llms.SingleLLMPromptExecutor
import ai.koog.agents.core.agent.AIAgent


class App {
    val greeting: String
        get() {
            return "Hello World!"
        }
}

fun main() = runBlocking {
    // Get an API key from the DEEPSEEK_API_KEY environment variable
    val apiKey = System.getenv("DEEPSEEK_API_KEY")
        ?: error("The API key is not set.")

    // Create an LLM client
    val deepSeekClient = DeepSeekLLMClient(apiKey)

    // Create an agent
    val agent = AIAgent(
        // Create a prompt executor using the LLM client
        promptExecutor = SingleLLMPromptExecutor(deepSeekClient),
        // Provide a model
        llmModel = DeepSeekModels.DeepSeekChat
    )

    // Run the agent
    val result = agent.run("Hello! How can you help me?")
    println(result)
}
